package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IFormView.FormType;
import com.lemasne.hms.interfaces.IModel;
import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.model.ChambreModel;
import com.lemasne.hms.model.ServiceModel;
import com.lemasne.hms.model.entities.Chambre;
import com.lemasne.hms.view.forms.ChambreFormView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;


public class ChambreController extends AbstractController<Chambre> implements ActionListener {
    
    private final IView parent;
    private ChambreFormView formView;
    
    public ChambreController(IModel model, IView view, IView parent) {
        super(Chambre.class, model, view);
        this.parent = parent;
        this.view.setActionListener(this);
        
        this.formView = new ChambreFormView((JFrame)this.parent, true);
        this.formView.setActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "add":
                this.formView.setFormType(FormType.ADD_FEATURE);
                this.formView.getServiceCombo().setModel(((ChambreModel)this.model).getChambreServicesComboBoxModel());
                this.formView.getSurveillantCombo().setModel(((ChambreModel)this.model).getChambreEmployesComboBoxModel());
                this.formView.setVisible(true);
            break;
            case "validate":
                System.out.println("validate");
            break;
            case "cancel":
                this.formView.dispose();
            break;
            default:
                System.out.println("Other action...");
            break;
        }
    }
}
