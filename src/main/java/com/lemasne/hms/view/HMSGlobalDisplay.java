/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lemasne.hms.view;

import java.awt.event.ActionListener;

/**
 *
 * @author U6018996
 */
public class HMSGlobalDisplay extends javax.swing.JPanel {

    /**
     * Creates new form ServiceView
     * @param caption
     * @param buttonCaption
     */
    public HMSGlobalDisplay(String caption, String buttonCaption) {
        initComponents();
        
        this.jLabel1.setText(caption);
        this.speedConnect.setText(buttonCaption);
    }
    
     public void setActionListener(ActionListener listener) {
        this.speedConnect.addActionListener(listener);
        this.speedConnect.setActionCommand("connect");
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
        speedConnect = new javax.swing.JButton();

        setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("Veuillez vous connecter...");

        speedConnect.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        speedConnect.setText("Se connecter");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(140, Short.MAX_VALUE)
                .addComponent(speedConnect)
                .addContainerGap(139, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(speedConnect)
                .addContainerGap(197, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton speedConnect;
    // End of variables declaration//GEN-END:variables
}
