package com.example.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.example.dao.Scripts.SQLSCRIPT;

public class DatabaseConnection {

    private static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());
    private String user;
    private String password;
    private String jdbcDriver;
    private String dbUrl;


    public DatabaseConnection(String user, String password, String jdbcDriver, String dbUrl) {
        this.user = user;
        this.password = password;
        this.jdbcDriver = jdbcDriver;
        this.dbUrl = dbUrl;
    }

    public  Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, user, password);
            Class.forName(jdbcDriver);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la connexion à la base de données", e);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Driver JDBC introuvable", e);
        }
        return conn;
    }

    public  void disconnect(Connection conn ) {
        try {
            conn.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Erreur lors de la fermeture de la connexion", e);
        }
    }

    public void createDb(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(SQLSCRIPT);
        } catch(SQLException se) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la création de la base de données", se);
        }
    }

}
