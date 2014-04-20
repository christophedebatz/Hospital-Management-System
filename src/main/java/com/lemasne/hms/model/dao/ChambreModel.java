package com.lemasne.hms.model.dao;

import com.lemasne.hms.model.entities.Employe;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ChambreModel extends AbstractDao<Employe> {
    
    
    public ChambreModel() {
        super(Employe.class, "numero");
    }
    
    public static List<Employe> getListWith(ResultSet result) {
        if (result == null) {
            throw new IllegalArgumentException("result cannot be null.");
        }

        List<Employe> employes = new ArrayList<>();
        try {
            while (result.next()) {
                employes.add(new Employe(
                        result.getInt("numero"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("adresse"),
                        result.getString("tel")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChambreModel.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not transtype to list of " + Employe.class.getSimpleName());
        }
        
        return employes;
    }
}
