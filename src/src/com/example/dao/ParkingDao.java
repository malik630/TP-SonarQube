package com.example.dao;

import com.example.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParkingDao {

    private Connection conn;
    private static final Logger LOGGER = Logger.getLogger(ParkingDao.class.getName());

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }



    public void insertParking(Parking parking) {
        String sql = "INSERT INTO parkings(name,address,capacity) VALUES (?,?,?); " ;
        try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, parking.getName());
            pstmt.setString(2, parking.getAddress());
            pstmt.setInt(3, parking.getCapacity());
            pstmt.executeUpdate();

        } catch (SQLException se) {
            LOGGER.log(Level.SEVERE, "Erreur lors de l'insertion du parking : " + parking.getName(), se);
        }

    }




}
