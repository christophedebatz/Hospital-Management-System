package com.lemasne.hms.view;

import com.lemasne.hms.interfaces.IView;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.ChangeListener;


public class FrontView extends JFrame implements IView {
    
    public FrontView(List<Component> tabViews, ChangeListener tabListener) {
        this.initComponents();

        for (Component view : tabViews) {
            this.homeTab.add(view.getName(), view);
        }
        
        this.homeTab.addChangeListener(tabListener);
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
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        homeTab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        labelIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/hospital.png"))); // NOI18N
        labelIcon.setName(""); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 0));
        jLabel2.setText("Hospital Management System");

        jMenu5.setText("Fichier");

        connexionMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        connexionMenuItem.setText("Connexion");
        jMenu5.add(connexionMenuItem);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Quitter");
        jMenu5.add(jMenuItem1);

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(0, 122, Short.MAX_VALUE)))
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
    private javax.swing.JMenuItem connexionMenuItem;
    private javax.swing.JTabbedPane homeTab;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel labelIcon;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setActionListener(ActionListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JTable getTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
