package com.lemasne.hms.view;

import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.settings.Constants;
import com.lemasne.hms.tools.Database;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;

public class FrontView extends JFrame implements IView {

    private HMSGlobalDisplay globalDisplay;

    public FrontView(List<Component> tabViews, ChangeListener tabListener, boolean connectAtStartup) {
        this.initComponents();
        this.setLocationRelativeTo(null);
        this.homeTab.addChangeListener(tabListener);

        if (connectAtStartup && Database.getInstance().getConnection() != null) {
            if (this.homeTab.getTabCount() > 0) {
                this.homeTab.removeAll();
            }
            for (Component view : tabViews) {
                this.homeTab.add(view.getName().toUpperCase(), view);
            }
        } else {
            this.globalDisplay = new HMSGlobalDisplay(
                "Veuillez vous connecter à la base de données pour continuer...",
                "Se connecter"
            );

            this.homeTab.addTab("HMS Service", this.globalDisplay);
        }
    }

    public void setItemListener(ItemListener listener) {
        this.checkboxActivateJoins.addItemListener(listener);
    }

    @Override
    public void setActionListener(ActionListener listener) {
        this.closeMenuItem.addActionListener(listener);
        this.closeMenuItem.setActionCommand("close");
        this.connexionMenuItem.addActionListener(listener);
        this.connexionMenuItem.setActionCommand("connect");

        if (this.globalDisplay != null) {
            this.globalDisplay.setActionListener(listener);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        homeTab = new javax.swing.JTabbedPane();
        labelIcon = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        connexionMenuItem = new javax.swing.JMenuItem();
        checkboxActivateJoins = new javax.swing.JCheckBoxMenuItem();
        closeMenuItem = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(Constants.SOFTWARE_NAME);
        setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        homeTab.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        labelIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/hospital.png"))); // NOI18N
        labelIcon.setName(""); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 44)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 0));
        jLabel2.setText("Hospital Management System");

        jMenuBar1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jMenu5.setText("Fichier");
        jMenu5.setName(""); // NOI18N

        connexionMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        connexionMenuItem.setText("Connexion");
        jMenu5.add(connexionMenuItem);

        checkboxActivateJoins.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, java.awt.event.InputEvent.ALT_MASK));
        checkboxActivateJoins.setText("Activer jointures");
        checkboxActivateJoins.setName(""); // NOI18N
        jMenu5.add(checkboxActivateJoins);

        closeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        closeMenuItem.setText("Quitter");
        jMenu5.add(closeMenuItem);

        jMenuBar1.add(jMenu5);

        jMenu3.setText("Mise à jour");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Historisation");
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(homeTab)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(0, 234, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelIcon))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel2)))
                .addGap(15, 15, 15)
                .addComponent(homeTab, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem checkboxActivateJoins;
    private javax.swing.JMenuItem closeMenuItem;
    private javax.swing.JMenuItem connexionMenuItem;
    private javax.swing.JTabbedPane homeTab;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel labelIcon;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getSearchContent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JTable getTable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addSearchBoxListener(DocumentListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
