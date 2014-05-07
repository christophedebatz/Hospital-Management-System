package com.lemasne.hms.model.dao;

import com.lemasne.hms.model.entities.Employe;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EmployeDao extends AbstractDao<Employe> {
    
    public EmployeDao() {
        super(Employe.class, "numero");
    }
    
    public List<Employe> getListWith(ResultSet result) {
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
                        result.getString("tel"),
                        result.getString("adresse")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not transtype to list of " + EmployeDao.class.getSimpleName());
        }
        
        return employes;
    }

    @Override
    public ResultSet findAllWithJoins() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
