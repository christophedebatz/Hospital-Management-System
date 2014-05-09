package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IDao;
import com.lemasne.hms.interfaces.IFormView;
import com.lemasne.hms.interfaces.IFormView.FormType;
import com.lemasne.hms.interfaces.IModel;
import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.model.EmployeModel;
import com.lemasne.hms.model.ServiceModel;
import com.lemasne.hms.model.entities.Employe;
import com.lemasne.hms.model.entities.Infirmier;
import com.lemasne.hms.model.entities.Service;
import com.lemasne.hms.tools.Helpers;
import com.lemasne.hms.tools.TemplateLoader;
import com.lemasne.hms.view.forms.InfirmierFormView;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class InfirmierController extends AbstractController<Infirmier> {

    private final InfirmierFormView formView;
    private final IView parent;

    public InfirmierController(IModel model, IView view, IView parent) {
        super(Infirmier.class, model, view);
        this.parent = parent;

        this.formView = new InfirmierFormView((JFrame) this.parent, true);
        this.formView.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        TemplateLoader.load(FrontController.currentSkin);
        SwingUtilities.updateComponentTreeUI(this.formView);
        
        switch (event.getActionCommand()) {
            case "add":
                this.formView.setFormType(IFormView.FormType.ADD_FEATURE);
                this.formView.getEmployeCombo().setModel(new EmployeModel().getComboBoxModel());
                this.formView.getServiceCombo().setModel(new ServiceModel().getComboBoxModel());
                this.formView.setVisible(true);
                break;
                
            case "update":
                this.formView.setFormType(IFormView.FormType.UPDATE_FEATURE);
                
                // get selected jtable selected row
                IDao dao = this.getModel().getDao();
                String[] values = Helpers.getKeyValues(this.view, dao);

                // retrieve chamber
                List<Infirmier> infirmiers = dao.getListWith(
                        dao.findById((Object[]) values)
                );

                Infirmier infirmier;
                if (!infirmiers.isEmpty()) { // if result not null
                    infirmier = infirmiers.get(0);
                    this.formView.setSalaire(infirmier.getSalaire());
                    this.formView.getRotationCombo().setSelectedItem(infirmier.getRotation().toUpperCase());

                    // retrieve associated service (with found code_service)
                    IModel employeModel = new EmployeModel();
                    List<Employe> employes = employeModel.getDao().getListWith(
                            employeModel.getDao().findAllByFieldsNames(
                                    new String[]{"numero"},
                                    new Object[]{infirmier.getNumero()}
                            )
                    );
                    
                    this.formView.getEmployeCombo().setModel(employeModel.getComboBoxModel());
                    
                     if (!employes.isEmpty()) { // select service
                        this.formView.getEmployeCombo().getModel().setSelectedItem((Employe) employes.get(0));
                    }
                    
                    // retrieve associated service (with found code_service)
                    IModel serviceModel = new ServiceModel();
                    List<Service> services = serviceModel.getDao().getListWith(
                            serviceModel.getDao().findAllByFieldsNames(
                                    new String[]{"code"},
                                    new Object[]{infirmier.getCode_service()}
                            )
                    );
                    
                    this.formView.getServiceCombo().setModel(serviceModel.getComboBoxModel());

                    if (!services.isEmpty()) { // select service
                        this.formView.getServiceCombo().getModel().setSelectedItem((Service) services.get(0));
                    }
                    
                    this.formView.setVisible(true);
                }
                
                else {
                    JOptionPane.showMessageDialog((Component) this.parent, "Cette donnée semble inexistante ou déjà supprimée/éditée.");
                    this.view.dispose();
                }
                
                break;

            case "validate":
                if (this.formView.getEmployeCombo().getSelectedItem() == null 
                        || this.formView.getServiceCombo().getSelectedItem() == null) {
                    JOptionPane.showMessageDialog((JFrame) this.parent, "Le formulaire n'a pas été correctement rempli.");
                    return;
                }

                final String numero = String.valueOf(((Employe)this.formView.getEmployeCombo().getSelectedItem()).getNumero());
                final String service = ((Service) this.formView.getServiceCombo().getSelectedItem()).getCode();
                final String rotation = (String) this.formView.getRotationCombo().getSelectedItem();
                final float salaire = this.formView.getSalaire();

                // adding something
                if (this.formView.getFormType() == FormType.ADD_FEATURE) {
                    Helpers.addToDatabase(this.model.getDao(), numero, service, rotation, salaire);
                   
                    // editing
                } else {

                    String[] keyValues = Helpers.getKeyValues(this.view, this.getModel().getDao());
                    
                    Map<String, Object> params = new HashMap<>();
                    params.put("numero", numero);
                    params.put("code_service", service);
                    params.put("rotation", rotation);
                    params.put("salaire", salaire);
                    
                    if (!this.model.getDao().updateById(params, (Object[]) keyValues)) {
                        JOptionPane.showMessageDialog((JFrame)this.parent, "Impossible de mettre à jour les données. Erreur: 47, Update AbstractDao");
                        return;
                    }
                }

                this.loadTable();
                this.formView.dispose();

                break;
                
            case "remove":
                Helpers.removeFromDatabase(this.view, this);
                break;
            
            default:
                this.formView.dispose();
                break;
        }
    }
}
