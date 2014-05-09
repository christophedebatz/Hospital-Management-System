package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IDao;
import com.lemasne.hms.interfaces.IFormView;
import com.lemasne.hms.interfaces.IModel;
import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.model.EmployeModel;
import com.lemasne.hms.model.entities.Docteur;
import com.lemasne.hms.model.entities.Employe;
import com.lemasne.hms.tools.Helpers;
import com.lemasne.hms.tools.TemplateLoader;
import com.lemasne.hms.view.forms.DocteurFormView;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class DocteurController extends AbstractController<Docteur> {
    
    private final DocteurFormView formView;
    private final IView parent;
    
    public DocteurController(IModel model, IView view, IView parent) {
        super(Employe.class, model, view);
        this.parent = parent;

        this.formView = new DocteurFormView((JFrame) this.parent, true);
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
                this.formView.setVisible(true);
                break;
                
            case "update":
                this.formView.setFormType(IFormView.FormType.UPDATE_FEATURE);
                
                // get selected jtable selected row
                IDao dao = this.getModel().getDao();
                String[] values = Helpers.getKeyValues(this.view, dao);

                // retrieve chamber
                List<Docteur> docteurs = dao.getListWith(
                        dao.findById((Object[]) values)
                );

                Docteur docteur;
                if (!docteurs.isEmpty()) { // if result not null
                    docteur = docteurs.get(0);
                    
                    // retrieve associated service (with found code_service)
                    IModel employeModel = new EmployeModel();
                    List<Employe> employes = employeModel.getDao().getListWith(
                            employeModel.getDao().findAllByFieldsNames(
                                    new String[]{"numero"},
                                    new Object[]{docteur.getNumero()}
                            )
                    );
                    
                    this.formView.getEmployeCombo().setModel(new EmployeModel().getComboBoxModel());
                    
                     if (!employes.isEmpty()) { // select service
                        this.formView.getEmployeCombo().getModel().setSelectedItem((Employe) employes.get(0));
                    }
                    
                    this.formView.getSpecialiteCombo().setSelectedItem(docteur.getSpecialite());
                    this.formView.setVisible(true);
                }
            
                 else {
                    JOptionPane.showMessageDialog((Component) this.parent, "Cette donnée semble inexistante ou déjà supprimée/éditée.");
                    this.view.dispose();
                }
                break;
                
            case "validate":
                if (this.formView.getEmployeCombo().getSelectedItem() == null 
                        || this.formView.getSpecialiteCombo().getSelectedItem() == null) {
                    JOptionPane.showMessageDialog((JFrame) this.parent, "Le formulaire n'a pas été correctement rempli.");
                    return;
                }
                
                final String numero = String.valueOf(((Employe)this.formView.getEmployeCombo().getSelectedItem()).getNumero());
                final String specialite = (String) this.formView.getSpecialiteCombo().getSelectedItem();
                
                // adding something
                if (this.formView.getFormType() == IFormView.FormType.ADD_FEATURE) {
                    Helpers.addToDatabase(this.model.getDao(), numero, specialite);
                   
                    // editing
                } else {
                    String[] keyValues = Helpers.getKeyValues(this.view, this.getModel().getDao());
                    
                    Map<String, Object> params = new HashMap<>();
                    params.put("numero", numero);
                    params.put("specialite", specialite);
                    
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
