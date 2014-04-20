package com.lemasne.hms.model.dao;

import com.lemasne.hms.model.entities.Hospitalisation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HospitalisationDao extends AbstractDao<Hospitalisation> {
    
    public HospitalisationDao() {
        super(Hospitalisation.class, "no_malade");
    }
    
    public static List<Hospitalisation> getListWith(ResultSet result) {
        if (result == null) {
            throw new IllegalArgumentException("result cannot be null.");
        }
        
        List<Hospitalisation> hospitalisations = new ArrayList<>();
        try {
            while (result.next()) {
                hospitalisations.add(new Hospitalisation(
                        result.getInt("no_malade"),
                        result.getString("code_service"),
                        result.getInt("no_chambre"),
                        result.getShort("lit")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HospitalisationDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not transtype to list of " + Hospitalisation.class.getSimpleName());
        }
        
        return hospitalisations;
    }
}
