package com.lemasne.hms.tools;

import com.lemasne.hms.settings.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Database {

    private static Database instance;
    private Config config;
    private final Connection connection;
    
    private Database() {
        this.config = Config.getInstance();
        this.connection = this.buildConnection(true);
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
    
    public boolean testConnectionWith(Config config) {
        Config oldConfig = this.config;
        this.config = config;
        boolean resultConn = false;
        
        if (this.buildConnection(false) != null) {
            this.config = oldConfig;
            return true;
        }
        else {
            this.config = oldConfig;
            return false;
        }
    }

    private Connection buildConnection(boolean showErrorDialog) {
        Connection conn = null;
        
        String connString = "jdbc:mysql://" 
                + this.config.get("serverAddress") 
                + ":" + this.config.get("serverPort") 
                + "/" + this.config.get("serverDbName")
                + "?connectTimeout=2000";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            if (showErrorDialog) {
                JOptionPane.showMessageDialog(
                    null, "Impossible de charger les drivers MySQL.\nVeuillez vérifiez vos informations de connexion.", null, JOptionPane.ERROR_MESSAGE, null
                );
                System.exit(1);
            }
            
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not load MySQL JDBC driver.");
        }

        try {
            conn = DriverManager.getConnection(
                    connString, 
                    this.config.get("serverUser"), 
                    this.config.get("serverPassword")
            );
            
        } catch (SQLException ex) {
            if (showErrorDialog) {
                JOptionPane.showMessageDialog(
                    null, "Impossible de se connecter à la base de donnée " + connString + ".\nVeuillez vérifiez vos informations de connexion.", null, JOptionPane.ERROR_MESSAGE, null
                );
                System.exit(1);
            }
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
