package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IDao;
import com.lemasne.hms.interfaces.IFormView.FormType;
import com.lemasne.hms.interfaces.IModel;
import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.model.ChambreModel;
import com.lemasne.hms.model.EmployeModel;
import com.lemasne.hms.model.ServiceModel;
import com.lemasne.hms.model.dao.ChambreDao;
import com.lemasne.hms.model.entities.Chambre;
import com.lemasne.hms.model.entities.Employe;
import com.lemasne.hms.model.entities.Service;
import com.lemasne.hms.tools.Helpers;
import com.lemasne.hms.view.forms.ChambreFormView;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ChambreController extends AbstractController<Chambre> implements ItemListener {

    private final IView parent;
    private final ChambreFormView formView;

    public ChambreController(IModel model, IView view, IView parent) {
        super(Chambre.class, model, view);
        this.parent = parent;

        this.formView = new ChambreFormView((JFrame) this.parent, true);
        this.formView.setItemListener(this);
        this.formView.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "add":
                this.formView.setFormType(FormType.ADD_FEATURE);
                this.formView.setNumero(((ChambreDao) this.model.getDao()).getMaxIdentifierIncrement() + 1);
                this.formView.getServiceCombo().setModel(((ChambreModel) this.model).getChambreServicesComboBoxModel());
                this.formView.getSurveillantCombo().setModel(
                        ((ChambreModel) this.model).getChambreInfirmiersComboBoxModel(
                                (Service) this.formView.getServiceCombo().getSelectedItem()
                        )
                );
                this.formView.setVisible(true);
            break;
                
                
            case "validate":
                
                if ("".equals(this.formView.getNumero())) {
                    JOptionPane.showMessageDialog((JFrame) this.parent, "Le numéro de la chambre est vide.");
                    return;
                }
                
                final String code = ((Service) this.formView.getServiceCombo().getSelectedItem()).getCode();
                final String numero = this.formView.getNumero();
                final String surveillant = String.valueOf(((Employe) this.formView.getSurveillantCombo().getSelectedItem()).getNumero());
                final String nb_lits = String.valueOf(this.formView.getNbLits());
                
                // add something
                if (this.formView.getFormType().equals(FormType.ADD_FEATURE)) {
                    this.model.getDao().insert( new ArrayList() {{
                            add(code);
                            add(numero);
                            add(surveillant);
                            add(nb_lits);
                        }}
                    );
                }
                
                // update something
                else {
                    String[] keyValues = Helpers.getKeyValues(this.view, this.getModel().getDao());
                    
                    Map<String, Object> params = new HashMap<>();
                    params.put("code_service", code);
                    params.put("no_chambre", numero);
                    params.put("surveillant", surveillant);
                    params.put("nb_lits", nb_lits);
                    
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
                
                
            case "update":
                this.formView.setFormType(FormType.UPDATE_FEATURE);

                // get selected jtable selected row
                IDao dao = this.getModel().getDao();
                String[] values = Helpers.getKeyValues(this.view, dao);

                // retrieve chamber
                List<Chambre> chambres = dao.getListWith(
                        dao.findById((Object[]) values)
                );

                Chambre chambre;
                if (!chambres.isEmpty()) { // if result not null
                    chambre = chambres.get(0);
                    this.formView.setNbLits(chambre.getNb_lits());
                    this.formView.setNumero(chambre.getNo_chambre());

                    // retrieve associated service (with found code_service)
                    IModel serviceModel = new ServiceModel();
                    List<Service> services = serviceModel.getDao().getListWith(
                            serviceModel.getDao().findAllByFieldsNames(
                                    new String[]{"code"},
                                    new Object[]{chambre.getCode_service()}
                            )
                    );

                    if (!services.isEmpty()) { // select service
                        this.formView.getServiceCombo().getModel().setSelectedItem((Service) services.get(0));
                    }

                    // retrieve surveillant
                    IModel employeModel = new EmployeModel();
                    List<Employe> employes = employeModel.getDao().getListWith(
                            employeModel.getDao().findAllByFieldsNames(
                                    new String[]{"numero"},
                                    new Object[]{chambre.getSurveillant()}
                            )
                    );

                    if (!employes.isEmpty()) { // select employe
                        this.formView.getSurveillantCombo().getModel().setSelectedItem((Employe) employes.get(0));
                    }

                    this.formView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog((Component) this.parent, "Cette donnée semble inexistante ou déjà supprimée/éditée.");
                    this.view.dispose();
                }
                break;
            default:
                this.formView.dispose();
                break;
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox comboBox = (JComboBox) e.getSource();
        if (comboBox == this.formView.getServiceCombo()) {
            this.formView.getSurveillantCombo().setModel(
                    ((ChambreModel) this.model).getChambreInfirmiersComboBoxModel(
                            (Service) this.formView.getServiceCombo().getSelectedItem()
                    )
            );
        }
    }
}
