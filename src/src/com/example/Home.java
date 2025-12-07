package com.example;

import com.example.dao.*;
import com.example.entity.*;

import java.sql.Connection;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Home {
    public static final String USER = "root";
    public static final String PASSWORD = System.getenv("DB_PASSWORD");
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/citiesdb";
    private static final java.util.logging.Logger LOGGER = Logger.getLogger(Home.class.getName());

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
            LOGGER.log(Level.INFO, "Arguments {0} {1} must be an integer.", new Object[]{args[0], args[1]});
            System.exit(1);
        }

    }
}
