package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IFormView;
import com.lemasne.hms.interfaces.IModel;
import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.model.ChambreModel;
import com.lemasne.hms.model.dao.ChambreDao;
import com.lemasne.hms.model.entities.Service;
import com.lemasne.hms.view.forms.ServiceFormView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;


public class ServiceController extends AbstractController<Service> implements ActionListener {

    private final IView parent;
    private ServiceFormView formView;
    
    public ServiceController(IModel model, IView view, IView parent) {
        super(Service.class, model, view);
        this.parent = parent;
        
        this.view.setActionListener(this);
        this.formView = new ServiceFormView((JFrame)this.parent, true);
        this.formView.setActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "add":
                this.formView.setFormType(IFormView.FormType.ADD_FEATURE);
                
                this.formView.setVisible(true);
            break;
            case "validate":
                
            break;
        }
    }
}
