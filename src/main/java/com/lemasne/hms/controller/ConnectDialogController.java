package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.settings.Config;
import com.lemasne.hms.view.ConnectDialogView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectDialogController implements ActionListener {
    
    private final ConnectDialogView view;
    
    public ConnectDialogController(IView view) {
        this.view = (ConnectDialogView) view;
        this.view.setLocationRelativeTo(this.view);
        this.view.setActionListener(this);
        
        this.view.fillForm(Config.getInstance());
        this.view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "close":
               this.view.dispose();
            break;
            case "use_ssh":
                this.view.toggleSSHSettings();
            break;
            case "validate":
                
                break;
        }
    }
}
