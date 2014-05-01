package com.lemasne.hms.model;

import com.lemasne.hms.model.dao.ServiceDao;
import com.lemasne.hms.model.entities.Service;
import javax.swing.table.TableModel;

public class ServiceModel extends AbstractModel<Service> {

    public ServiceModel() {
        super(Service.class, new ServiceDao());
    }

    @Override
    public TableModel getCustomizeTableModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
