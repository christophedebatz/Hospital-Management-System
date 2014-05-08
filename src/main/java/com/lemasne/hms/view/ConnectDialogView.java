package com.lemasne.hms.view;


import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.settings.Config;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;

public class ConnectDialogView extends javax.swing.JDialog implements IView {
    
    public ConnectDialogView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public void dispose () {
        super.dispose();
    }
    
    public void fillForm (Config config) {
        this.serverAddress.setText(config.get("serverAddress"));
        this.serverPort.setText(config.get("serverPort"));
        this.serverDbName.setText(config.get("serverDbName"));
        this.serverUser.setText(config.get("serverUser"));
        this.serverPassword.setText(config.get("serverPassword"));
        this.sshPassword.setText(config.get("sshPassword"));
        this.sshUser.setText(config.get("sshUser"));
        this.useSSH.setSelected(config.get("sshEnabled").equals("true"));
        this.connectAtStartup.setSelected(config.get("connectAtStartup").equals("true"));
        this.toggleSSHSettings(config.get("sshEnabled").equals("true"));
    }
    
    
    public Config fillConfig() {
        Config config = Config.getInstance();
        config.set("serverAddress", this.serverAddress.getText());
        config.set("serverPort", this.serverPort.getText());
        config.set("serverDbName", this.serverDbName.getText());
        config.set("serverUser", this.serverUser.getText());
        config.set("serverPassword", this.serverPassword.getText());
        config.set("sshPassword", this.sshPassword.getText());
        config.set("sshUser", this.sshUser.getText());
        config.set("sshEnabled", String.valueOf(this.useSSH.isSelected()));
        config.set("connectAtStartup", String.valueOf(this.connectAtStartup.isSelected()));
        return config;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        serverAddress = new javax.swing.JTextField();
        serverPort = new javax.swing.JTextField();
        serverUser = new javax.swing.JTextField();
        storeDatabaseParameters = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        close = new javax.swing.JButton();
        useSSH = new javax.swing.JCheckBox();
        sshPanel = new javax.swing.JPanel();
        sshPassword = new javax.swing.JTextField();
        sshUser = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        serverPassword = new javax.swing.JTextField();
        connectAtStartup = new javax.swing.JCheckBox();
        serverDbName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Connexion à la base de données");
        setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        setModal(true);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Adresse du serveur :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Port du serveur :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Mot de passe :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Identifiant :");

        serverAddress.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        serverPort.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        serverUser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        storeDatabaseParameters.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        storeDatabaseParameters.setText("CONNEXION");

        close.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        close.setText("ANNULER");

        useSSH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        useSSH.setText("Se connecter via tunnel SSH");

        sshPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Paramètres SSH", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        sshPanel.setVisible(false);

        sshPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        sshUser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Mot de passe :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Identifiant :");

        javax.swing.GroupLayout sshPanelLayout = new javax.swing.GroupLayout(sshPanel);
        sshPanel.setLayout(sshPanelLayout);
        sshPanelLayout.setHorizontalGroup(
            sshPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sshPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sshPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(sshPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sshUser, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(sshPassword))
                .addContainerGap())
        );
        sshPanelLayout.setVerticalGroup(
            sshPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sshPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sshPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sshUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(sshPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sshPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        serverPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        connectAtStartup.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        connectAtStartup.setSelected(true);
        connectAtStartup.setText("Connexion automatique au démarrage de HMS");

        serverDbName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Nom de la base :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sshPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(close)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(storeDatabaseParameters, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel7))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(serverPort, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(175, 175, 175))
                                    .addComponent(serverDbName)))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(24, 24, 24)
                                .addComponent(serverAddress))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(serverUser, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                    .addComponent(serverPassword))))
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(connectAtStartup)
                            .addComponent(useSSH))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverDbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(serverUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(serverPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(connectAtStartup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(useSSH)
                .addGap(18, 18, 18)
                .addComponent(sshPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(storeDatabaseParameters)
                    .addComponent(close))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton close;
    private javax.swing.JCheckBox connectAtStartup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField serverAddress;
    private javax.swing.JTextField serverDbName;
    private javax.swing.JTextField serverPassword;
    private javax.swing.JTextField serverPort;
    private javax.swing.JTextField serverUser;
    private javax.swing.JPanel sshPanel;
    private javax.swing.JTextField sshPassword;
    private javax.swing.JTextField sshUser;
    private javax.swing.JButton storeDatabaseParameters;
    private javax.swing.JCheckBox useSSH;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setActionListener(ActionListener listener) {
        this.close.addActionListener(listener);
        this.close.setActionCommand("close");
        this.useSSH.addActionListener(listener);
        this.useSSH.setActionCommand("use_ssh");
        this.storeDatabaseParameters.addActionListener(listener);
        this.storeDatabaseParameters.setActionCommand("validate");
    }

    public void toggleSSHSettings(boolean forceVisibility) {
        int sizeDiff = !forceVisibility ? -this.sshPanel.getSize().height : this.sshPanel.getSize().height;
        this.sshPanel.setVisible(forceVisibility);
        this.setSize(this.getSize().width, this.getSize().height + sizeDiff);
        this.pack();
    }
    
    public void toggleSSHSettings() {
        int sizeDiff = this.sshPanel.isVisible() ? -this.sshPanel.getSize().height : this.sshPanel.getSize().height;
        this.sshPanel.setVisible(!this.sshPanel.isVisible());
        this.setSize(this.getSize().width, this.getSize().height + sizeDiff);
        this.pack();
    }
    
    @Override
    public JTable getTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addSearchBoxListener(DocumentListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSearchContent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSelectionListener(ListSelectionListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEnabledRemoving(boolean enabled) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEnabledUpdating(boolean enabled) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
