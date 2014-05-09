package com.lemasne.hms.model.dao;

import com.lemasne.hms.model.entities.Docteur;
import com.lemasne.hms.model.entities.Employe;
import com.lemasne.hms.model.entities.Malade;
import com.lemasne.hms.model.entities.Soigne;
import com.lemasne.hms.tools.QueryBuilder;
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
        ResultSet result = null;

        try {
            result = this.dbc.prepareStatement(
                    QueryBuilder.select("Malade.prenom as prenom_malade", "Malade.prenom as nom_malade", "Employe.prenom as prenom_docteur", "Employe.nom as nom_docteur", "specialite")
                    .from(entityClass)
                    .leftJoin(Docteur.class)
                        .on("no_docteur", "Docteur.numero")
                    .leftJoin(Malade.class)
                        .on("no_malade", "Malade.numero")
                    .leftJoin(Employe.class)
                        .on("no_docteur", "Employe.numero").toSQL()
            ).executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ChambreDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not execute query : " + ex.getMessage());
        }

        return result;
    }
}
