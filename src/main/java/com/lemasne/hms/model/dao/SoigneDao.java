package com.lemasne.hms.model.dao;

import com.lemasne.hms.model.entities.Soigne;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SoigneDao extends AbstractDao {
    
    public SoigneDao() {
        super(Soigne.class, "no_docteur", "no_malade");
    }
    
    public List<Soigne> getListWith(ResultSet result) {
        if (result == null) {
            throw new IllegalArgumentException("result cannot be null.");
        }

        List<Soigne> soignes = new ArrayList<>();
        try {
            while (result.next()) {
                soignes.add(new Soigne(
                    result.getInt("no_docteur"),
                    result.getInt("no_malade")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SoigneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not transtype to list of " + Soigne.class.getSimpleName());
        }

        return soignes;
    }
    
    @Override
    public ResultSet findAllWithJoins() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
