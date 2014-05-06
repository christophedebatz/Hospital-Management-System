package com.lemasne.hms.tools;

import com.lemasne.hms.settings.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Database {

    private static Database instance;
    private final Connection connection;
    
    private Database() {
        this.connection = this.buildConnection();
    }
    
    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
    public Connection getConnection() {
        return this.connection;
    }

    private Connection buildConnection() {

        Connection conn = null;
        Config config = Config.getInstance();
        
        String connString = "jdbc:mysql://" 
                + config.get("serverAddress") 
                + ":" + config.get("serverPort") 
                + "/" + config.get("serverDbName");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not load MySQL JDBC driver.");
        }

        try {
            conn = DriverManager.getConnection(
                    connString, 
                    config.get("serverUser"), 
                    config.get("serverPassword")
            );
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not connect to the database server.");
        }

        return conn;
    }

    public void dispose() {
        if (this.connection != null) {
            try {
                if (!this.connection.isClosed()) {
                    this.connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not close the current connection.");
            }
        }
    }
}
