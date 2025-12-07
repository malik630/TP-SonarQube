package com.example.dao;

import com.example.entity.ParkingPlace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParkingPlaceDao {

    private Connection conn;
    private static final Logger LOGGER = Logger.getLogger(ParkingPlaceDao.class.getName());

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }



    public void insertParkingPlace(ParkingPlace parkingPlace) {
        String sql = "INSERT INTO parking_places(place_name,place_status,parking_id) VALUES (?,?,?); " ;

        try (PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1, parkingPlace.getPlaceName());
            pstmt.setString(2, parkingPlace.getPlaceStatus().name());
            pstmt.setInt(3, parkingPlace.getParking().getParkingId());
            pstmt.executeUpdate();

        } catch (SQLException se) {
            LOGGER.log(Level.SEVERE, "Erreur lors de l'insertion de la place de parking : " + parkingPlace.getPlaceName(), se);
        }
    }



}
