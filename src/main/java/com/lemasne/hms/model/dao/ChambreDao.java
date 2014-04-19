package com.lemasne.hms.model.dao;

import com.lemasne.hms.model.Chambre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ChambreDao extends AbstractDao<Chambre> {
    
    public ChambreDao() {
        super(Chambre.class, "code_service", "no_chambre");
    }
    
    public static List<Chambre> getListWith(ResultSet result) {
        if (result == null) {
            throw new IllegalArgumentException("result cannot be null.");
        }

        List<Chambre> chambres = new ArrayList<>();
        try {
            while (result.next()) {
                chambres.add(new Chambre(
                        result.getInt("no_chambre"),
                        result.getString("code_service"),
                        result.getInt("surveillant"),
                        result.getInt("nb_lits")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChambreDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not transtype to list of " + ChambreDao.class.getSimpleName());
        }
        
        return chambres;
    }
}
