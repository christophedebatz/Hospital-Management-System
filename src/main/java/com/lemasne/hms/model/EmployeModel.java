package com.lemasne.hms.model;

import com.lemasne.hms.model.dao.ChambreDao;
import com.lemasne.hms.model.dao.EmployeDao;
import com.lemasne.hms.model.dao.ServiceDao;
import com.lemasne.hms.model.entities.Employe;
import com.lemasne.hms.model.entities.Service;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.TableModel;

public class EmployeModel extends AbstractModel<Employe> {

    public EmployeModel() {
        super(Employe.class, new EmployeDao());
    }
    
    public DefaultComboBoxModel getChambreServicesComboBoxModel() {
        if (this.dao != null) {
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            List<Service> services = (new ServiceDao()).getListWith(((ChambreDao)this.dao).findServicesNames());
            for (Service s : services) {
                model.addElement((Service) s);
            }
            return model;
        }
        return null;
    }
    
    @Override
    public TableModel getCustomizeTableModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
