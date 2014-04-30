package com.lemasne.hms.interfaces;

import java.sql.ResultSet;
import java.util.Map;


public interface IDao {
    public ResultSet findById(Object... keysValues);
    public ResultSet findAll();
    public int removeById(Object... keysValues);
    public boolean updateFromId(Map<String, Object> entityParams, Object... columnsKeysValues);
}
