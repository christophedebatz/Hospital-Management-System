package com.lemasne.hms.model;

import com.lemasne.hms.model.dao.SoigneDao;
import com.lemasne.hms.model.entities.Soigne;
import javax.swing.table.TableModel;

public class SoigneModel extends AbstractModel<Soigne> {

    public SoigneModel() {
        super(Soigne.class, new SoigneDao());
    }

    @Override
    public TableModel getCustomizeTableModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
