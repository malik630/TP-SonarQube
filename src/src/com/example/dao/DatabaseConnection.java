package com.example.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private String user;
    private String password;
    private String jdbc_driver;
    private String db_url;


    public DatabaseConnection(String user, String password, String jdbc_driver, String db_url) {
        this.user = user;
        this.password = password;
        this.jdbc_driver = jdbc_driver;
        this.db_url = db_url;
    }

    public  Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(db_url, user, password);
            Class.forName(jdbc_driver);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public  void disconnect(Connection conn ) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDb(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(Scripts.sql);
            stmt.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            }
        }
    }

}
