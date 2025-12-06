package com.test;

import com.example.Prototype;
import com.example.dao.*;
import com.example.entity.*;
import com.example.service.ReservationManager;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.text.ParseException;

public class TestReservationManager {

    static DatabaseConnection databaseConnection;
    static Connection connection;




    // Integration Test Cancel Reservation
    @Test
    public void testIntegrationCancelReservation() throws ParseException {
        databaseConnection = new DatabaseConnection("root","","org.h2.Driver","jdbc:h2:mem:test");
        connection = databaseConnection.connect();
        databaseConnection.createDb(connection);
        // insert Parking
        Prototype prototype = new Prototype();
        prototype.constructPrototypeUser(connection);
        prototype.constructPrototypeReservation(prototype.getUser(), prototype.getParkingPlace(), connection);
        // get Reservation to delete
        ReservationManager reservationManager = new ReservationManager();
        ReservationDao reservationDao = prototype.getReservationDao();
        reservationManager.setReservationDao(reservationDao);
        reservationManager.cancelReservation(1);
        // get canceled reservation
        Reservation reservationCanceled = reservationDao.getReservationById(1);
        assertEquals("CANCELLED",reservationCanceled.getStatus().name());

    }




}
