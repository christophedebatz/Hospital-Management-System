package com.lemasne.hms.model.dao;

import com.lemasne.hms.model.entities.Chambre;
import com.lemasne.hms.model.entities.Employe;
import com.lemasne.hms.model.entities.Hospitalisation;
import com.lemasne.hms.model.entities.Malade;
import com.lemasne.hms.model.entities.Service;
import com.lemasne.hms.tools.QueryBuilder;
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
    
    public List<Hospitalisation> getListWith(ResultSet result) {
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

    @Override
    public ResultSet findAllWithJoins() {
        ResultSet result = null;

        try {
            result = this.dbc.prepareStatement(
                    QueryBuilder.select("no_malade", "Malade.prenom as prenom_malade", "Malade.prenom as nom_malade", "Service.nom as nom_service", "Hospitalisation.no_chambre", "Employe.nom as nom_surveillant", "lit")
                    .from(entityClass)
                    .leftJoin(Service.class)
                        .on("code_service", "code")
                    .leftJoin(Malade.class)
                        .on("Hospitalisation.no_malade", "Malade.numero")
                    .leftJoin(Chambre.class)
                        .on("Hospitalisation.no_chambre", "Chambre.no_chambre")
                    .leftJoin(Employe.class)
                        .on("Chambre.surveillant", "Employe.numero").toSQL()
            ).executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ChambreDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not execute query : " + ex.getMessage());
        }

        return result;
    }
}
