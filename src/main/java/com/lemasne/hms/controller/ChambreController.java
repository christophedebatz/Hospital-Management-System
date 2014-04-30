package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IController;
import com.lemasne.hms.interfaces.IModel;
import com.lemasne.hms.interfaces.IView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChambreController implements IController, ActionListener {

    private IModel model;
    private IView view;
    
    public ChambreController(IModel model, IView view) {
        
        if (model == null || view == null) {
            throw new IllegalArgumentException("Model or view or both are null.");
        }
        
        this.model = model;
        this.view = view;
        this.view.setVisible(true);
        this.view.setActionListener(this);
    }
    
    public void loadTable() {
        this.view.getTable().setModel(this.model.getTableModel());
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "search":
                System.out.println("Searching...");
                break;
            default:
                System.out.println("Other action...");
                break;
        }
    }
}
