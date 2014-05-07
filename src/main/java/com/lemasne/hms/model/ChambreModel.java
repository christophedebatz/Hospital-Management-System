package com.lemasne.hms.model;

import com.lemasne.hms.model.dao.ChambreDao;
import com.lemasne.hms.model.dao.EmployeDao;
import com.lemasne.hms.model.dao.ServiceDao;
import com.lemasne.hms.model.entities.Chambre;
import com.lemasne.hms.model.entities.Employe;
import com.lemasne.hms.model.entities.Service;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.TableModel;

public class ChambreModel extends AbstractModel<Chambre> {

    public ChambreModel() {
        super(Chambre.class, new ChambreDao());
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
    
    public DefaultComboBoxModel getChambreEmployesComboBoxModel() {
        if (this.dao != null) {
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            List<Employe> employes = (new EmployeDao()).getListWith(((ChambreDao)this.dao).findEmployesNames());
            for (Employe s : employes) {
                model.addElement((Employe) s);
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
