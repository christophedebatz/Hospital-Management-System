package com.lemasne.hms.model;

import com.lemasne.hms.interfaces.IDao;
import com.lemasne.hms.interfaces.IModel;
import com.lemasne.hms.model.dao.ServiceDao;
import com.lemasne.hms.tools.DatabaseHelpers;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ServiceModel implements IModel {

    private final IDao sDao;

    public ServiceModel() {
        this.sDao = new ServiceDao();
    }

    public DefaultTableModel getTableModel() {
        return DatabaseHelpers.getTableModel(this.sDao);
    }

    @Override
    public TableModel getCustomizeTableModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
