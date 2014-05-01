package com.lemasne.hms.interfaces;

import java.awt.event.ActionListener;
import javax.swing.JTable;


public interface IView {
    public void setVisible(boolean visibility);
    public void setActionListener(ActionListener listener);
    public JTable getTable();
    public String getName();
    public void dispose();
}
