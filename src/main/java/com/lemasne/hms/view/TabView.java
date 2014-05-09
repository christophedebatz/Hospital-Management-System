package com.lemasne.hms.view;

import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.tools.TextPrompt;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;


public final class TabView extends javax.swing.JPanel implements IView {
    
    public TabView() {
    }
    
    public TabView(String name) {
        super.setName(name);
        initComponents();
        addComponentBehaviors();
    }
    
    public JTable getTable() {
        return this.resultsList;
    }
    
    public String getSearchContent () {
        return this.searchBox.getText();
    }
    
    @Override
    public void setEnabledRemoving(boolean enabled) {
        this.removeButton.setEnabled(enabled);
    }
    
    @Override
    public void setEnabledUpdating(boolean enabled) {
        this.updateButton.setEnabled(enabled);
    }
    
    @Override
    public void addSearchBoxListener(DocumentListener listener) {
        this.searchBox.getDocument().addDocumentListener(listener);
    }
    
    @Override
    public void setActionListener(ActionListener listener) {
        this.addButton.setActionCommand("add");
        this.addButton.addActionListener(listener);
        this.removeButton.setActionCommand("remove");
        this.removeButton.addActionListener(listener);
        this.updateButton.setActionCommand("update");
        this.updateButton.addActionListener(listener);
    }
    
    @Override
    public void setSelectionListener(ListSelectionListener listener) {
        this.resultsList.getSelectionModel().addListSelectionListener(listener);
    }
    
    public void addComponentBehaviors() {
        TextPrompt tp = new TextPrompt("Rechercher un(e) " + super.getName().toLowerCase() + "...", this.searchBox);
        tp.setForeground(Color.DARK_GRAY);
        tp.changeAlpha(170);        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        resultsList = new javax.swing.JTable();
        searchBox = new javax.swing.JTextField();
        removeButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();

        addButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/File-add-icon.png"))); // NOI18N
        addButton.setText("AJOUTER");

        resultsList.setAutoCreateRowSorter(true);
        resultsList.setName("resultsList"); // NOI18N
        jScrollPane.setViewportView(resultsList);

        searchBox.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N

        removeButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        removeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/remove.png"))); // NOI18N
        removeButton.setText("SUPPRIMER");
        removeButton.setEnabled(false);

        updateButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        updateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/update.png"))); // NOI18N
        updateButton.setText("MODIFIER");
        updateButton.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(searchBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(removeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton)
                    .addComponent(removeButton)
                    .addComponent(updateButton))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1008, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JButton removeButton;
    private javax.swing.JTable resultsList;
    private javax.swing.JTextField searchBox;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
