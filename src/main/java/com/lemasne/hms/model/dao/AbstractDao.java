package com.lemasne.hms.model.dao;

import com.lemasne.hms.interfaces.IDao;
import com.lemasne.hms.tools.Database;
import com.lemasne.hms.tools.QueryBuilder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class AbstractDao<T> implements IDao<T> {

    protected final Class entityClass;
    protected final String[] keysNames;
    protected Connection dbc;

    protected AbstractDao(Class entityClass, String... keysNames) {
        this.entityClass = entityClass;
        this.keysNames = keysNames;
        this.dbc = Database.getInstance().getConnection();
    }

    @Override
    public ResultSet findById(Object... keysValues) {
        ResultSet result = null;

        try {
            result = this.dbc.prepareStatement(
                    QueryBuilder.select("*")
                    .from(entityClass)
                    .whereRange(keysNames, keysValues).toSQL()
            ).executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not execute query : " + ex.getMessage());
        }

        return result;
    }

    @Override
    public boolean insert(List<String> values) {
        if (!values.isEmpty()) {
            try {
                return this.dbc.prepareStatement(
                        QueryBuilder.insertInto(entityClass)
                        .values(values).toSQL()
                ).execute();
            } catch (SQLException ex) {
                Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public ResultSet findAll() {
        ResultSet result = null;

        try {
            result = this.dbc.prepareStatement(
                    QueryBuilder.select("*").from(entityClass).toSQL()).executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not execute query : " + ex.getMessage());
        }

        return result;
    }

    @Override
    public int removeById(Object... keysValues
    ) {
        try {
            return this.dbc.prepareStatement(
                    QueryBuilder.delete()
                    .from(entityClass)
                    .whereRange(keysNames, keysValues).toSQL()
            ).executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not execute query : " + ex.getMessage());
            return 0;
        }
    }

    @Override
    public boolean updateFromId(Map<String, Object> entityParams, Object... columnsKeysValues
    ) {
        if (!entityParams.isEmpty()) {
            String[] params = new String[entityParams.size()];
            Object[] values = new String[entityParams.size()];
            int i = 0;

            for (Map.Entry<String, Object> param : entityParams.entrySet()) {
                params[i] = param.getKey();
                values[i] = param.getValue();
                i++;
            }

            try {
                return this.dbc.prepareStatement(
                        QueryBuilder.update(params)
                        .from(entityClass)
                        .set(values)
                        .whereRange(keysNames, columnsKeysValues).toSQL()
                ).execute();
            } catch (SQLException ex) {
                Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not execute query : " + ex.getMessage());
            }
        }
        return false;
    }

    public abstract ResultSet findAllWithJoins();
}
