package com.lemasne.hms.model.dao;

import com.lemasne.hms.model.Malade;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MaladeDao extends AbstractDao<Malade> {
    
    public MaladeDao() {
        super(Malade.class, "numero");
    }
    
    public static List<Malade> getListWith(ResultSet result) {
        if (result == null) {
            throw new IllegalArgumentException("result cannot be null.");
        }

        List<Malade> malades = new ArrayList<>();
        try {
            while (result.next()) {
                malades.add(new Malade(
                        result.getInt("numero"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("tel"),
                        result.getString("adresse"),
                        result.getString("mutuelle")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaladeDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not transtype to list of " + Malade.class.getSimpleName());
        }

        return malades;
    }
}