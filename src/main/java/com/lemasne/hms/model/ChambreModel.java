package com.lemasne.hms.model;

import com.lemasne.hms.interfaces.IDao;
import com.lemasne.hms.model.dao.ChambreDao;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class ChambreModel {

    private IDao cDao;

    public ChambreModel(ChambreDao cDao) {
        this.cDao = (ChambreDao) cDao;
    }

    public DefaultTableModel getTableModel() {
        if (this.cDao == null) {
            return null;
        }
        try {
            ResultSet data = this.cDao.findAll();
            ResultSetMetaData metadata = data.getMetaData();
            int columnCount = metadata.getColumnCount();
            String[] columnNames = new String[columnCount];
            Object[][] result = new Object[data.getFetchSize()][columnCount];
            
            for (int column = 1; column <= columnCount; column++) {
                columnNames[column] = metadata.getColumnName(column);
            }
            
            int i = 0;
            while (data.next()) {
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    result[i][columnIndex] = data.getObject(columnIndex);
                }
                i++;
            }
            
            return new DefaultTableModel(result, columnNames);
        } catch (SQLException ex) {
            Logger.getLogger(ChambreModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
