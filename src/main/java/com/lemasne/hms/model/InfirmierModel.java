package com.lemasne.hms.model;

import com.lemasne.hms.model.dao.InfirmierDao;
import com.lemasne.hms.model.entities.Infirmier;
import javax.swing.table.TableModel;

public class InfirmierModel extends AbstractModel<Infirmier> {

    public InfirmierModel() {
        super(Infirmier.class, new InfirmierDao());
    }

    @Override
    public TableModel getCustomizeTableModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
