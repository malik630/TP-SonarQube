package com.example;

import com.example.dao.*;
import com.example.entity.*;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Prototype {
    private User user;
    private ParkingPlace parkingPlace;
    private ReservationDao reservationDao;

    public void constructPrototypeUser(Connection connection) {

        // insert Parking
        Parking parking = new Parking();
        parking.setName("Parking 1");
        parking.setAddress("Alger");
        parking.setCapacity(30);
        ParkingDao parkingDao = new ParkingDao();
        parkingDao.setConn(connection);
        parkingDao.insertParking(parking);
        // insert Parking Place
        ParkingPlaceDao parkingPlaceDao = new ParkingPlaceDao();
        parkingPlaceDao.setConn(connection);
        this.parkingPlace = new ParkingPlace();
        parkingPlace.setIdPlace(1);
        parking.setParkingId(1);
        parkingPlace.setParking(parking);
        parkingPlace.setPlaceName("F5");
        parkingPlace.setPlaceStatus(PlaceStatus.AVAILABLE);
        parkingPlaceDao.insertParkingPlace(parkingPlace);
        // insert user
        UserDao userDao = new UserDao();
        userDao.setConn(connection);
        this.user = new User();
        user.setUserId(1);
        user.setName("Ali");
        user.setPhone("055555555");
        user.setEmail("ali@gmail.com");
        userDao.insertUser(user);
    }

    public void constructPrototypeReservation(User user, ParkingPlace parkingPlace, Connection connection) throws ParseException {
        this.reservationDao = new ReservationDao();
        reservationDao.setConn(connection);
        Reservation reservation = new Reservation();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setUser(user);
        reservation.setParkingPlace(parkingPlace);
        reservation.setStartTime(dateFormat.parse("22-10-2023 10:00"));
        reservation.setEndTime(dateFormat.parse("22-10-2023 11:45"));
        reservationDao.insertReservation(reservation);
    }

    public User getUser() {
        return user;
    }

    public ParkingPlace getParkingPlace() {
        return parkingPlace;
    }

    public ReservationDao getReservationDao() {
        return reservationDao;
    }
}
