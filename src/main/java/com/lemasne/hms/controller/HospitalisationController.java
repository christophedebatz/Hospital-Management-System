package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IModel;
import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.model.entities.Hospitalisation;
import com.lemasne.hms.tools.Helpers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HospitalisationController extends AbstractController<Hospitalisation> implements ActionListener {

    public HospitalisationController(IModel model, IView view) {
        super(Hospitalisation.class, model, view);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "remove":
                Helpers.removeFromDatabase(this.view, this);
            break;
        }
    }
}
