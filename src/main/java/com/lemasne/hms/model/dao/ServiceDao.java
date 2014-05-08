package com.lemasne.hms.model.dao;

import com.lemasne.hms.model.entities.Employe;
import com.lemasne.hms.model.entities.Service;
import com.lemasne.hms.tools.QueryBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceDao extends AbstractDao<Service> {
    
    public ServiceDao() {
        super(Service.class, "code");
    }
    
    @Override
    public List<Service> getListWith(ResultSet result) {
        if (result == null) {
            throw new IllegalArgumentException("result cannot be null.");
        }

        List<Service> services = new ArrayList<>();
        try {
            while (result.next()) {
                services.add(new Service(
                        result.getString("code"),
                        result.getString("nom"),
                        result.getString("batiment"),
                        result.getString("directeur")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not transtype to list of " + Service.class.getSimpleName());
        }

        return services;
    }
    
    @Override
    public ResultSet findAllWithJoins() {
        ResultSet result = null;

        try {
            result = this.dbc.prepareStatement(
                    QueryBuilder.select("code as code_service", "Service.nom as nom_service", "batiment", "prenom as prenom_directeur", "Employe.nom as nom_directeur")
                    .from(entityClass)
                    .leftJoin(Employe.class)
                    .on("directeur", "numero").toSQL()
            ).executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not execute query : " + ex.getMessage());
        }

        return result;
    }

    @Override
    public boolean updateFromId(Map entityParams, Object... columnsKeysValues) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}