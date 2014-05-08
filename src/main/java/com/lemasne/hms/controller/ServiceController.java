package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IFormView;
import com.lemasne.hms.interfaces.IModel;
import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.model.EmployeModel;
import com.lemasne.hms.model.entities.Employe;
import com.lemasne.hms.model.entities.Service;
import com.lemasne.hms.tools.Helpers;
import com.lemasne.hms.view.forms.ServiceFormView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ServiceController extends AbstractController<Service> implements ActionListener {

    private final IView parent;
    private final ServiceFormView formView;

    public ServiceController(IModel model, IView view, IView parent) {
        super(Service.class, model, view);
        this.parent = parent;

        this.formView = new ServiceFormView((JFrame) this.parent, true);
        this.formView.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "add":
                this.formView.setFormType(IFormView.FormType.ADD_FEATURE);
                this.formView.getDirecteurCombo().setModel((new EmployeModel()).getDirecteursComboBoxModel());
                this.formView.setVisible(true);
                break;
            case "validate":
                String codeService = this.formView.getCodeField();
                String nomService = this.formView.getNomService();
                String batimentService = this.formView.getBatiment();
                Employe directeurService = (Employe) this.formView.getDirecteurCombo().getSelectedItem();

                if (codeService.length() > 0 && nomService.length() > 0 && batimentService.length() > 0 && directeurService != null) {

                    List<String> toInsert = new ArrayList<>();
                    toInsert.add(codeService);
                    toInsert.add(nomService);
                    toInsert.add(batimentService);
                    toInsert.add(String.valueOf(directeurService.getNumero()));
                    this.model.getDao().insert(toInsert);
                    this.loadTable();

                    this.formView.dispose();
                } else {
                        JOptionPane.showMessageDialog((JFrame)this.parent, "Le formulaire n'a pas été correctement rempli.");
                    }
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
