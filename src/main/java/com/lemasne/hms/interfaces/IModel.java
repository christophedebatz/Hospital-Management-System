package com.lemasne.hms.interfaces;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public interface IModel {
    public DefaultTableModel getTableModel();
    public TableModel getCustomizeTableModel();
    public DefaultTableModel getJoinedTableModel();
}
