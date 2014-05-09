package com.lemasne.hms.model.dao;

import com.lemasne.hms.model.entities.Malade;
import com.lemasne.hms.tools.QueryBuilder;
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
    
    public List<Malade> getListWith(ResultSet result) {
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
    public int getMaxIdentifierIncrement() {
        ResultSet result;
        try {
            result = this.dbc.prepareStatement(
                        QueryBuilder.select("max(numero) as vMax")
                            .from(entityClass).toSQL()
            ).executeQuery();

            if (result.next()) { // simple scalar result
                return (int) result.getInt("vMax");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChambreDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not transtype to list of " + ChambreDao.class.getSimpleName());
        }

        return -1;
    }
    
    
    @Override
    public ResultSet findAllWithJoins() {
        return super.findAll();
    }
}