package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IFormView;
import com.lemasne.hms.interfaces.IFormView.FormType;
import com.lemasne.hms.interfaces.IModel;
import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.model.entities.Chambre;
import com.lemasne.hms.view.forms.ChambreFormView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChambreController extends AbstractController<Chambre> implements ActionListener {
    
    private IFormView formView;
    
    public ChambreController(IModel model, IView view) {
        super(Chambre.class, model, view);
        this.view.setActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "search":
                System.out.println("Searching...");
                break;
            case "add":
                this.formView = new ChambreFormView(null, true, FormType.ADD_FEATURE, null);
                this.formView.setActionListener(this);
            break;
            case "cancel":
                System.out.println("cancel add");
                break;
            default:
                System.out.println("Other action...");
            break;
        }
    }
}
