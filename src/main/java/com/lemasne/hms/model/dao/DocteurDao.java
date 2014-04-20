package com.lemasne.hms.model.dao;

import com.lemasne.hms.model.entities.Docteur;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DocteurDao extends AbstractDao<Docteur> {
    
    public DocteurDao() {
        super(Docteur.class, "numero");
    }
    
    public static List<Docteur> getListWith(ResultSet result) {
        if (result == null) {
            throw new IllegalArgumentException("result cannot be null.");
        }

        List<Docteur> docteurs = new ArrayList<>();
        try {
            while (result.next()) {
                docteurs.add(new Docteur(
                        result.getInt("numero"),
                        result.getString("specialite")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocteurDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not transtype to list of " + DocteurDao.class.getSimpleName());
        }
        
        return docteurs;
    }
}
