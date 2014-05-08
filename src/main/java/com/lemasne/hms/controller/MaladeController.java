package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IModel;
import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.model.entities.Malade;
import com.lemasne.hms.tools.Helpers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MaladeController extends AbstractController<Malade> implements ActionListener {
    
    public MaladeController(IModel model, IView view) {
        super(Malade.class, model, view);
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
