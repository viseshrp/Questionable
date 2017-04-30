/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.questionable.utility;

import com.questionable.models.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author viseshprasad
 */
public class UserDB {

        public static int addUser(User user) throws IOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO user (id, user_name, password, email, type, reg_date) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getUser_name());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getType());
            ps.setString(6, user.getReg_date());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }

    public static User getUser(String email) throws IOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM user "
                + "WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                //id, email, password, email, type, reg_date
                user.setId(rs.getInt("id"));
                user.setUser_name(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setType(rs.getString("type"));
                user.setReg_date(rs.getString("reg_date"));
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

        public static User getUser(int id) throws IOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM user "
                + "WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                //id, user_name, password, user_name, type, reg_date
                user.setId(rs.getInt("id"));
                user.setUser_name(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setType(rs.getString("type"));
                user.setReg_date(rs.getString("reg_date"));
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<User> getUsers() throws IOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM user";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<User> users = new ArrayList<User>();
            while (rs.next()) {
                User user = new User();
                //id, user_name, password, email, type, reg_date
                user.setId(rs.getInt("id"));
                user.setUser_name(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setType(rs.getString("type"));
                user.setReg_date(rs.getString("reg_date"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }
}
