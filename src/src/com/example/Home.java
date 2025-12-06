package com.example;

import com.example.dao.*;
import com.example.entity.*;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.sql.Connection;
import java.text.ParseException;

public class Home {
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/citiesdb";
    private static final Logger logger = LoggerFactory.getLogger(Home.class);

    public static void main(String[] args) throws ParseException {
        DatabaseConnection db = new DatabaseConnection(USER, PASSWORD, JDBC_DRIVER, DB_URL);
        Connection connection = db.connect();
        Prototype prototype = new Prototype();
        try {
            if (args.length > 0) {
                prototype.constructPrototypeUser(connection);
                prototype.constructPrototypeReservation(prototype.getUser(), prototype.getParkingPlace(), connection);
            }
        } catch (NumberFormatException e) {
            logger.info(() -> "Arguments " + args[0] + " " + args[1] + " must be an integer.");
            System.exit(1);
        }

    }
}
