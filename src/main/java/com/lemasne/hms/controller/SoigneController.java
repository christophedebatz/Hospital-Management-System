package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IModel;
import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.model.entities.Soigne;
import com.lemasne.hms.tools.Helpers;
import java.awt.event.ActionEvent;


public class SoigneController extends AbstractController<Soigne> {

    public SoigneController(IModel model, IView view) {
        super(Soigne.class, model, view);
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
