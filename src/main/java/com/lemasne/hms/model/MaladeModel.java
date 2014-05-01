package com.lemasne.hms.model;

import com.lemasne.hms.model.dao.MaladeDao;
import com.lemasne.hms.model.entities.Malade;
import javax.swing.table.TableModel;

public class MaladeModel extends AbstractModel<Malade> {

    public MaladeModel() {
        super(Malade.class, new MaladeDao());
    }

    @Override
    public TableModel getCustomizeTableModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
