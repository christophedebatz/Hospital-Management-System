package com.lemasne.hms.controller;

import com.lemasne.hms.controller.dto.ControllerDTO;
import com.lemasne.hms.interfaces.IController;
import com.lemasne.hms.interfaces.IView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectDialogController implements IController, ActionListener {
    
    private IView view;
    
    public ConnectDialogController(IView view) {
        this.view = view;
        this.view.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "close":
               this.view.dispose();
            break;
        }
    }

    @Override
    public void setControllerDto(ControllerDTO controllerDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
