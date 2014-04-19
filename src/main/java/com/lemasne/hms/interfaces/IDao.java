package com.lemasne.hms.interfaces;

import java.sql.ResultSet;
import java.util.Map;


public interface IDao {
    
    ResultSet findById(Object... keysValues);
    ResultSet findAll();
    int removeById(Object... keysValues);
    boolean updateFromId(Map<String, Object> entityParams, Object... columnsKeysValues);
    
}
