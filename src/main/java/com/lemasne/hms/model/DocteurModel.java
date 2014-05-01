package com.lemasne.hms.model;

import com.lemasne.hms.model.dao.DocteurDao;
import com.lemasne.hms.model.entities.Docteur;
import javax.swing.table.TableModel;

public class DocteurModel extends AbstractModel<Docteur> {

    public DocteurModel() {
        super(Docteur.class, new DocteurDao());
    }
    
    @Override
    public TableModel getCustomizeTableModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
