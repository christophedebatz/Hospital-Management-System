package com.lemasne.hms.tools;

import com.lemasne.hms.interfaces.IDao;
import com.lemasne.hms.model.ChambreModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class DatabaseHelpers {
    
    public static DefaultTableModel getTableModel(IDao dao) {
        if (dao == null) {
            return null;
        }
        try {
            ResultSet data = dao.findAll();
            DefaultTableModel model = new DefaultTableModel();
            ResultSetMetaData metadata = data.getMetaData();
            int columnCount = metadata.getColumnCount();

            for (int i = 0; i < columnCount; i++) {
                model.addColumn(metadata.getColumnName(i + 1).toUpperCase().replaceAll("_", " "));
            }

            while (data.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 0; i < rowData.length; ++i) {
                    rowData[i] = data.getObject(i + 1);
                }
                model.addRow(rowData);
            }

            return model;
        } catch (SQLException ex) {
            Logger.getLogger(ChambreModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
