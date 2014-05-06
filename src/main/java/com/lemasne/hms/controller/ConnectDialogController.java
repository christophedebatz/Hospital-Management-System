package com.lemasne.hms.controller;

import com.lemasne.hms.controller.dto.ControllerDTO;
import com.lemasne.hms.interfaces.IController;
import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.settings.Config;
import com.lemasne.hms.tools.Database;
import com.lemasne.hms.view.ConnectDialogView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ConnectDialogController implements ActionListener {
    
    private final ConnectDialogView view;
    private final IController parentCtrl;
    
    public ConnectDialogController(IView view, IController parentCtrl) {
        this.parentCtrl = parentCtrl;
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
                if (Database.getInstance().getConnection() == null) {
                    JOptionPane.showMessageDialog(
                        this.view, "Impossible de se connecte à la base de donnée.\nVeuillez vérifiez vos informations de connexion.",null, JOptionPane.ERROR_MESSAGE, null
                    );
                } else {
                    this.parentCtrl.setControllerDto(
                            new ControllerDTO("launch_connexion")
                    );
                    this.parentCtrl.executeDTORequest();
                    this.view.dispose();
                }
            break;
        }
    }
}
