package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IDao;
import com.lemasne.hms.interfaces.IFormView;
import com.lemasne.hms.interfaces.IFormView.FormType;
import com.lemasne.hms.interfaces.IModel;
import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.model.EmployeModel;
import com.lemasne.hms.model.dao.EmployeDao;
import com.lemasne.hms.model.entities.Employe;
import com.lemasne.hms.model.entities.Service;
import com.lemasne.hms.tools.Helpers;
import com.lemasne.hms.view.forms.ServiceFormView;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ServiceController extends AbstractController<Service> {

    private final IView parent;
    private final ServiceFormView formView;

    public ServiceController(IModel model, IView view, IView parent) {
        super(Service.class, model, view);
        this.parent = parent;

        this.formView = new ServiceFormView((JFrame) this.parent, true);
        this.formView.setActionListener(this);
    }

    // ADD (modified)
    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {

            case "add":
                this.formView.setFormType(IFormView.FormType.ADD_FEATURE);
                this.formView.getDirecteurCombo().setModel(new EmployeModel().getComboBoxModel());
                this.formView.setVisible(true);
                break;

            case "update":
                this.formView.setFormType(FormType.UPDATE_FEATURE);

                // get selected jtable selected row
                IDao dao = this.getModel().getDao();
                String[] values = Helpers.getKeyValues(this.view, dao);

                List<Service> services = dao.getListWith(
                        dao.findById((Object[]) values)
                );

                Service service;
                if (!services.isEmpty()) { // if result not null
                    service = services.get(0);

                    this.formView.setBatiment(service.getBatiment());
                    this.formView.setCode(service.getCode());
                    this.formView.setNomService(service.getNom());

                    // retrieve associated directeur name (with found directeur no)
                    IModel employeModel = new EmployeModel();
                    List<Employe> employes = employeModel.getDao().getListWith(
                            employeModel.getDao().findAllByFieldsNames(
                                    new String[]{"numero"},
                                    new Object[]{service.getDirecteur()}
                            )
                    );

                    // fill directeur combobox
                    this.formView.getDirecteurCombo().setModel(new EmployeModel().getComboBoxModel());
                    
                    if (!employes.isEmpty()) { // select directeur
                        this.formView.getDirecteurCombo().getModel().setSelectedItem((Employe) employes.get(0));
                    }
                    
                    this.formView.setVisible(true);
                }
                
                else {
                    JOptionPane.showMessageDialog((Component) this.parent, "Cette donnée semble inexistante ou déjà supprimée/éditée.");
                    this.view.dispose();
                }
                break;

            case "validate":
                final String codeService = this.formView.getCodeField();
                final String nomService = this.formView.getNomService();
                final String batimentService = this.formView.getBatiment();
                final Employe directeurService = (Employe) this.formView.getDirecteurCombo().getSelectedItem();

                if (codeService.length() == 0 || nomService.length() == 0 || batimentService.length() == 0 || directeurService == null) {
                    JOptionPane.showMessageDialog((JFrame) this.parent, "Le formulaire n'a pas été correctement rempli.");
                    return;
                }

                // adding
                if (this.formView.getFormType() == FormType.ADD_FEATURE) {
                    this.model.getDao().insert(new ArrayList() {{
                            add(codeService);
                            add(nomService);
                            add(batimentService);
                            add(String.valueOf(directeurService.getNumero()));
                        }
                    });
                } 

                // editing
                else {
                    String[] keyValues = Helpers.getKeyValues(this.view, this.getModel().getDao());
                    
                    Map<String, Object> params = new HashMap<>();
                    params.put("code", codeService);
                    params.put("nom", nomService);
                    params.put("batiment", batimentService);
                    params.put("directeur", directeurService.getNumero());
                    
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