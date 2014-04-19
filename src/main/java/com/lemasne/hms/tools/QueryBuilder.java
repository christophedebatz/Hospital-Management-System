package com.lemasne.hms.tools;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class QueryBuilder {
    
    private enum QueryActionType {
        SELECT,
        UPDATE,
        DELETE
    }
    
    private String tableName;
    private QueryActionType actionType;
    private List<String> fields;
    private Map<String, Object> whereClauses;
    private Map<String, Object> updateClauses;

    private QueryBuilder (List fields, QueryActionType actionType) {
        this.fields = fields;
        this.actionType = actionType;
        this.whereClauses = new HashMap<>();
        
        if (this.actionType == QueryActionType.UPDATE) {
            this.updateClauses = new HashMap<>();
        }
    }
    
    private QueryBuilder (QueryActionType actionType) {
        this(null, actionType);
    }

    public static QueryBuilder select(String... fields) {
        return new QueryBuilder(
                Arrays.asList(fields),
                QueryActionType.SELECT
        );
    }
    public static QueryBuilder delete() {
        return new QueryBuilder(QueryActionType.DELETE);
    }
    
    public static QueryBuilder update(String... fields) {
        return new QueryBuilder(
                Arrays.asList(fields),
                QueryActionType.UPDATE
        );
    }
    
    public QueryBuilder set(Object... fieldsValues) {
        if (this.actionType == QueryActionType.UPDATE)
        {
            if (fieldsValues.length != this.fields.size()) {
                throw new IllegalArgumentException("Could not map fields with fields values.");
            }
            for (int i = 0; i < this.fields.size(); i++) {
                this.updateClauses.put(this.fields.get(i), fieldsValues[i]);
            }
        }
        return this;
    }
    
    public QueryBuilder from(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public QueryBuilder from(Class entityType) {
        return this.from(entityType.getSimpleName());
    }
    
    public QueryBuilder where(String fieldName, Object value) {
        this.whereClauses.put(fieldName, value);
        return this;
    }
    
    public QueryBuilder whereRange(String[] fieldsNames, Object[] fieldsValues) {
        if (fieldsNames.length != fieldsValues.length) {
            throw new IllegalArgumentException("Cannot parse whereRange arguments.");
        }
        for (int i = 0; i < fieldsNames.length; i++) {
            this.whereClauses.put(fieldsNames[i], fieldsValues[i]);
        }
        return this;
    }
    
    public QueryBuilder addWhere(String fieldName, Object value) {
        return this.where(fieldName, value);
    }
    
    public String toSQL() {
        StringBuilder sb = new StringBuilder();
        
        if (this.actionType == QueryActionType.SELECT)
        {
            sb.append("select ");
            int i = 0;
            for (String field : this.fields) {
                sb.append(field);
                if (i < this.fields.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append(" from  ").append(this.tableName);
        }
        
        else if (this.actionType == QueryActionType.DELETE)
        {
            sb.append("delete from ").append(this.tableName);
        }
        
        else
        {
            sb.append("update ").append(this.tableName).append(" set");
            String separator = "";
            int i = 0;
            
            for (Map.Entry<String, Object> updateClause : this.updateClauses.entrySet()) {
                if (!(updateClause.getValue() instanceof Integer) && !(updateClause.getValue() instanceof Long)) {
                    separator = "'";
                }
                sb.append(" ").append(updateClause.getKey())
                  .append(" = ").append(separator).append(updateClause.getValue()).append(separator);
                
                if (i < this.updateClauses.size()) {
                    sb.append(", ");
                }
            }
        }
        
        if (this.whereClauses.size() > 0)
        {
            String separator = "";
            sb.append(" where");
            int i = 0;
            
            for (Map.Entry<String, Object> whereClause : this.whereClauses.entrySet()) {
                if (!(whereClause.getValue() instanceof Integer) && !(whereClause.getValue() instanceof Long)) {
                    separator = "'";
                }
                sb.append(" ").append(whereClause.getKey())
                  .append(" = ").append(separator).append(whereClause.getValue()).append(separator);
                
                if (i < this.whereClauses.size()) {
                    sb.append(", ");
                }
            }
        }
        
        return  sb.toString();
    }
    
    @Override
    public String toString() {
        return this.toSQL();
    }
}