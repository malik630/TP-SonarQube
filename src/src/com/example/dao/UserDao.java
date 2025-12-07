package com.example.dao;

import com.example.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserDao {

    private Connection conn;
    private static final Logger LOGGER = Logger.getLogger(UserDao.class.getName());

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }




    public void insertUser(User user) {

        String sql = "INSERT INTO users(name,email,phone) VALUES (?,?,?); " ;

        try (PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPhone());
            pstmt.executeUpdate();

        } catch (SQLException se) {
            LOGGER.log(Level.SEVERE, "Erreur lors de l'insertion de l'utilisateur : "+user.getName(), se);
        }

    }



    public User getUserById(int userId) {
        User user = null;
        String sql = "SELECT * FROM Users WHERE user_id  = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setInt(1,userId);
            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    user = new User();
                    user.setUserId(rs.getInt("user_id "));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                }
            }


        } catch (SQLException se) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la récupération de l'utilisateur : "+userId, se);
        }
        return user;
    }
}
