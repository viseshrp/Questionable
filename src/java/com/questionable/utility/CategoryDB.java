/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.questionable.utility;

import com.questionable.models.Category;
import com.questionable.models.Post;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author viseshprasad
 */
public class CategoryDB {

    public static int addCategory(Category category) throws IOException {
        //ConnectionPool pool = ConnectionPool.getInstance();
        PreparedStatement ps = null;
        Connection connection = null;
        String query
                = "INSERT INTO category (id, name, user_id) "
                + "VALUES (?, ?, ?)";
        try {
             connection = ConnectionPool.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, category.getId());
            ps.setString(2, category.getName());
            ps.setInt(3, category.getUser().getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDB.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public static Category getCategory(int id) throws IOException {
        //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        String query = "SELECT * FROM category "
                + "WHERE id = ?";
        try {
             connection = ConnectionPool.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Category category = null;
            if (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setUser(UserDB.getUser(rs.getInt("user_id")));
            }
            return category;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            try {
                //pool.freeConnection(connection);
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static Category getCategoryByName(String name) throws IOException {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        String query = "SELECT * FROM category "
                + "WHERE name = ?";
        try {
             connection = ConnectionPool.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            rs = ps.executeQuery();
            Category category = null;
            if (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setUser(UserDB.getUser(rs.getInt("user_id")));
            }
            return category;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            try {
                //pool.freeConnection(connection);
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDB.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
