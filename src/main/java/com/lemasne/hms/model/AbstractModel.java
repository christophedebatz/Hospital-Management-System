package com.lemasne.hms.model;

import com.lemasne.hms.interfaces.IDao;
import com.lemasne.hms.interfaces.IModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

abstract class AbstractModel<T> implements IModel {

    protected Class entityType;
    protected IDao dao;

    public AbstractModel(Class entityType, IDao dao) {
        this.entityType = entityType;
        this.dao = dao;
    }
    
    @Override
    public IDao getDao () {
        return this.dao;
    }
    
    @Override
    public DefaultTableModel getTableModel() {
        return this.getTableModel(false);
    }
    
    @Override
    public DefaultTableModel getJoinedTableModel() {
        return this.getTableModel(true);
    }

    @Override
    public abstract TableModel getCustomizeTableModel();

    @Override
    public DefaultComboBoxModel getComboBoxModel() {
        if (this.dao != null) {
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            List<T> list = this.dao.getListWith(this.dao.findAll());
            
            for (T c : list) {
                model.addElement((T) c);
            }
            return model;
        }
        return null;
    }
    
    protected DefaultTableModel getTableModel(boolean isJoined) {
        if (this.dao == null) {
            return null;
        }
        try {
            ResultSet data;

            if (!isJoined) {
                data = this.dao.findAll();
            } else {
                data = this.dao.findAllWithJoins();
            }
            DefaultTableModel model = new DefaultTableModel();
            ResultSetMetaData metadata = data.getMetaData();
            int columnCount = metadata.getColumnCount();

            for (int i = 0; i < columnCount; i++) {
                model.addColumn(metadata.getColumnLabel(i + 1).toUpperCase().replaceAll("_", " "));
            }
            
            boolean isEmpty = true;
            while (data.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 0; i < rowData.length; ++i) {
                    rowData[i] = data.getObject(i + 1);
                }
                model.addRow(rowData);
                isEmpty = false;
            }

            if (isEmpty) {
                Object[] empty = new Object[columnCount];
                empty[0] = "Aucun enregistrement...";
                model.addRow(empty);
            }
            
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
