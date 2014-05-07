package com.lemasne.hms.interfaces;

import java.awt.Container;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.event.DocumentListener;

public interface IView {
    public void setVisible(boolean visibility);
    public void setActionListener(ActionListener listener);
    public JTable getTable();
    public String getSearchContent();
    public String getName();
    public void dispose();
    public void addSearchBoxListener(DocumentListener listener);
    public Container getParent();
}
