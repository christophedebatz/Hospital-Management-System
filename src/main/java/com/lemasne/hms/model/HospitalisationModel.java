package com.lemasne.hms.model;

import com.lemasne.hms.model.dao.HospitalisationDao;
import com.lemasne.hms.model.entities.Hospitalisation;
import javax.swing.table.TableModel;

public class HospitalisationModel extends AbstractModel<Hospitalisation> {

    public HospitalisationModel() {
        super(Hospitalisation.class, new HospitalisationDao());
    }

    @Override
    public TableModel getCustomizeTableModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
