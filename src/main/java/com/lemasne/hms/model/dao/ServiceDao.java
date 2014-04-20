package com.lemasne.hms.model.dao;

import com.lemasne.hms.model.entities.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceDao extends AbstractDao<Service> {
    
    public ServiceDao() {
        super(Service.class, "code");
    }
    
    
    public static List<Service> getListWith(ResultSet result) {
        if (result == null) {
            throw new IllegalArgumentException("result cannot be null.");
        }

        List<Service> services = new ArrayList<>();
        try {
            while (result.next()) {
                services.add(new Service(
                        result.getInt("code"),
                        result.getString("nom"),
                        result.getString("batiment").charAt(0),
                        result.getString("directeur")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not transtype to list of " + Service.class.getSimpleName());
        }

        return services;
    }
}