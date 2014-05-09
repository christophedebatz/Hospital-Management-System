package com.lemasne.hms.controller;

import com.lemasne.hms.controller.dto.ControllerDTO;
import com.lemasne.hms.interfaces.IController;
import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.settings.Config;
import com.lemasne.hms.tools.Database;
import com.lemasne.hms.tools.SSHTunnel;
import com.lemasne.hms.tools.TemplateLoader;
import com.lemasne.hms.view.ConnectDialogView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ConnectDialogController implements ActionListener {

    private final ConnectDialogView view;
    private final IController parentCtrl;

    public ConnectDialogController(IView view, IController parentCtrl) {
        this.parentCtrl = parentCtrl;
        this.view = (ConnectDialogView) view;
        this.view.setLocationRelativeTo(this.view);
        this.view.setActionListener(this);
        
        TemplateLoader.load(FrontController.currentSkin);
        SwingUtilities.updateComponentTreeUI(this.view);

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
                Config newConfig = this.view.fillConfig();

                if (newConfig.get("sshEnabled").equals("true")) {
                    SSHTunnel ssh = new SSHTunnel(newConfig.get("sshUser"), newConfig.get("sshPassword"));
                    if (!ssh.connect()) {
                        JOptionPane.showMessageDialog(
                                this.view, "Impossible de créer le tunnel SSH pour " + newConfig.get("sshUser") + ".", null, JOptionPane.ERROR_MESSAGE, null
                        );
                        return;
                    }
                }

                if (!Database.getInstance().testConnectionWith(newConfig)) {
                    JOptionPane.showMessageDialog(
                            this.view, "Impossible de se connecter à la base de donnée.\nVeuillez vérifiez vos informations de connexion.", null, JOptionPane.ERROR_MESSAGE, null
                    );
                    return;
                }

                if (!Config.save(newConfig)) {
                    JOptionPane.showMessageDialog(
                            this.view, "Impossible d'enregistrer les informations de connexion.", null, JOptionPane.ERROR_MESSAGE, null
                    );
                    return;
                }

                this.view.dispose();
                this.parentCtrl.setControllerDto(new ControllerDTO("launch_connexion")).executeDTORequest();
                break;
        }
    }
}
