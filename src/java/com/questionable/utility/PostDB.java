/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.questionable.utility;

import com.questionable.models.Post;
import com.questionable.models.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author viseshprasad
 */
public class PostDB {

    public static int addPost(Post post) throws IOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO post (id, question, content, user_id, category_id, created_date, modified_date, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, post.getId());
            ps.setString(2, post.getQuestion());
            ps.setString(3, post.getContent());
            ps.setInt(4, post.getUser().getId());
            ps.setInt(5, post.getCategory().getId());
            ps.setString(6, post.getCreated_date());
            ps.setString(7, post.getModified_date());
            ps.setString(8, post.getStatus());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }

    public static Post getPost(int id) throws IOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM post "
                + "WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Post post = null;
            if (rs.next()) {
                post = new Post();
                //id, user_name, password, user_name, type, reg_date
                post.setId(rs.getInt("id"));
                post.setQuestion(rs.getString("question"));
                post.setContent(rs.getString("content"));
                post.setUser(UserDB.getUser(rs.getInt("user_id")));
                post.setCategory(CategoryDB.getCategory(rs.getInt("category_id")));
                post.setCreated_date(rs.getString("created_date"));
                post.setModified_date(rs.getString("modified_date"));
                post.setStatus(rs.getString("status"));
            }
            return post;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Post> getPosts() throws IOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM post";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Post> posts = new ArrayList<>();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setQuestion(rs.getString("question"));
                post.setContent(rs.getString("content"));
                post.setUser(UserDB.getUser(rs.getInt("user_id")));
                post.setCategory(CategoryDB.getCategory(rs.getInt("category_id")));
                post.setCreated_date(rs.getString("created_date"));
                post.setModified_date(rs.getString("modified_date"));
                post.setStatus(rs.getString("status"));
                posts.add(post);
            }
            return posts;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

    public static ArrayList<Post> getPostsByUser(int user_id) throws IOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM post "
                + "WHERE user_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            ArrayList<Post> posts = new ArrayList<>();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setQuestion(rs.getString("question"));
                post.setContent(rs.getString("content"));
                post.setUser(UserDB.getUser(rs.getInt("user_id")));
                post.setCategory(CategoryDB.getCategory(rs.getInt("category_id")));
                post.setCreated_date(rs.getString("created_date"));
                post.setModified_date(rs.getString("modified_date"));
                post.setStatus(rs.getString("status"));
                posts.add(post);
            }
            return posts;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

    public static ArrayList<Post> getPostsByStatus(String status) throws IOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM post "
                + "WHERE status = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, status);
            rs = ps.executeQuery();
            ArrayList<Post> posts = new ArrayList<>();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setQuestion(rs.getString("question"));
                post.setContent(rs.getString("content"));
                post.setUser(UserDB.getUser(rs.getInt("user_id")));
                post.setCategory(CategoryDB.getCategory(rs.getInt("category_id")));
                post.setCreated_date(rs.getString("created_date"));
                post.setModified_date(rs.getString("modified_date"));
                post.setStatus(rs.getString("status"));
                posts.add(post);
            }
            return posts;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

    public static int updatePost(int post_id, Post post) throws IOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE post SET "
                + "question = ?, "
                + "content = ?, "
                + "user_id = ?, "
                + "category_id = ?, "
                + "created_date = ?, "
                + "modified_date = ?, "
                + "status = ? "
                + "WHERE id = ?";

        try {
            ps = connection.prepareStatement(query);

            ps.setString(1, post.getQuestion());
            ps.setString(2, post.getContent());
            ps.setInt(3, post.getUser().getId());
            ps.setInt(4, post.getCategory().getId());
            ps.setString(5, post.getCreated_date());
            ps.setString(6, post.getModified_date());
            ps.setString(7, post.getStatus());
            ps.setInt(8, post_id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

        public static int deletePost(int post_id) throws IOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        //DELETE FROM post WHERE id = 22;
        String query = "DELETE FROM post "
                + "WHERE id = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, post_id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

}
