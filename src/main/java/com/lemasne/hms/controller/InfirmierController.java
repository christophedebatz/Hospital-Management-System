package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IFormView;
import com.lemasne.hms.interfaces.IModel;
import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.model.ServiceModel;
import com.lemasne.hms.model.entities.Infirmier;
import com.lemasne.hms.tools.Helpers;
import com.lemasne.hms.view.forms.InfirmierFormView;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;


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
        switch (event.getActionCommand()) {
            case "add":
                this.formView.setFormType(IFormView.FormType.ADD_FEATURE);
                this.formView.getServiceCombo().setModel(new ServiceModel().getComboBoxModel());
                this.formView.setVisible(true);
                break;
            case "remove":
                Helpers.removeFromDatabase(this.view, this);
            break;
        }
    }
}
