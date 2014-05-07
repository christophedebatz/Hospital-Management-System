package com.lemasne.hms.model.dao;

import com.lemasne.hms.model.entities.Chambre;
import com.lemasne.hms.model.entities.Employe;
import com.lemasne.hms.model.entities.Service;
import com.lemasne.hms.tools.QueryBuilder;
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

    @Override
    public List<Chambre> getListWith(ResultSet result) {
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

    public ResultSet findServicesNames() {
        ResultSet result = null;
        
        try {
            result = this.dbc.prepareStatement(
                        QueryBuilder.select("code, nom, batiment, directeur")
                        .from(entityClass)
                        .leftJoin(Service.class)
                        .on("code_service", "code").toSQL()
                    ).executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ChambreDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not execute query : " + ex.getMessage());
        }
        
        return result;
    }

    public ResultSet findEmployesNames() {
        ResultSet result = null;
        
        try {
            result = this.dbc.prepareStatement(
                        QueryBuilder.select("numero, nom, prenom, tel, adresse")
                        .from(entityClass)
                        .leftJoin(Employe.class)
                        .on("surveillant", "numero").toSQL()
                    ).executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ChambreDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not execute query : " + ex.getMessage());
        }
        
        return result;
    }
    
    @Override
    public ResultSet findAllWithJoins() {
        ResultSet result = null;

        try {
            result = this.dbc.prepareStatement(
                    QueryBuilder.select("no_chambre", "nb_lits", "batiment", "Service.nom as nom_service", "prenom as prenom_surveillant", "Employe.nom as nom_surveillant")
                    .from(entityClass)
                    .leftJoin(Service.class)
                    .on("code_service", "code")
                    .leftJoin(Employe.class)
                    .on("surveillant", "numero").toSQL()
            ).executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ChambreDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not execute query : " + ex.getMessage());
        }

        return result;
    }
}
