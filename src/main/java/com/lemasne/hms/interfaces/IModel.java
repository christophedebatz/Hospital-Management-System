package com.lemasne.hms.interfaces;

import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public interface IModel {
    public DefaultTableModel getTableModel();
    public TableModel getCustomizeTableModel();
    public DefaultTableModel getJoinedTableModel();
    public DefaultComboBoxModel getComboBoxModel();
    public DefaultComboBoxModel getComboBoxModel(ResultSet resultData);
    public IDao getDao ();
}
