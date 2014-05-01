package com.lemasne.hms.model.dao;

import com.lemasne.hms.model.entities.Employe;
import com.lemasne.hms.model.entities.Infirmier;
import com.lemasne.hms.model.entities.Service;
import com.lemasne.hms.tools.QueryBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class InfirmierDao extends AbstractDao<Infirmier> {
    
    public InfirmierDao() {
        super(Infirmier.class, "numero");
    }
    
    public static List<Infirmier> getListWith(ResultSet result) {
        if (result == null) {
            throw new IllegalArgumentException("result cannot be null.");
        }
        
        List<Infirmier> infirmiers = new ArrayList<>();
        try {
            while (result.next()) {
                infirmiers.add(new Infirmier(
                        result.getInt("numero"),
                        result.getString("code_service"),
                        result.getString("rotation"),
                        result.getFloat("lit")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InfirmierDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not transtype to list of " + Infirmier.class.getSimpleName());
        }
        
        return infirmiers;
    }

    @Override
    public ResultSet findAllWithJoins() {
        ResultSet result = null;

        try {
            result = this.dbc.prepareStatement(
                    QueryBuilder.select("Employe.prenom as prenom", "Employe.nom as nom", "tel", "Service.nom as nom_service", "rotation", "salaire")
                    .from(entityClass)
                    .leftJoin(Service.class)
                    .on("code_service", "code")
                    .leftJoin(Employe.class)
                    .on("Infirmier.numero", "Employe.numero").toSQL()
            ).executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ChambreDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not execute query : " + ex.getMessage());
        }

        return result;
    }
}
