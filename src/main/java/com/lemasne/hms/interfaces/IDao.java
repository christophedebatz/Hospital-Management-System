package com.lemasne.hms.interfaces;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;


public interface IDao<T> {
    public ResultSet findById(Object... keysValues);
    public ResultSet findAll();
    public ResultSet findAllWithJoins();
    public int removeById(Object... keysValues);
    public boolean updateFromId(Map<String, Object> entityParams, Object... columnsKeysValues);
    public List<T> getListWith(ResultSet result);
    public boolean insert(List<String> values);
}
