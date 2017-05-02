/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.questionable.controllers;

import com.questionable.models.Category;
import com.questionable.models.Comment;
import com.questionable.models.Post;
import com.questionable.models.User;
import com.questionable.utility.CategoryDB;
import com.questionable.utility.CommentDB;
import com.questionable.utility.PostDB;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *Class to control flow of posting, editing, deleting, reporting and commenting
 * @author viseshprasad
 */
public class PostController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("theUser");
        // get current action
        String action = request.getParameter("action");
        
        //Check if user is in session
        if (action == null) {
            url = "/home.jsp";
            
            //action of viewing a post in a separate screen
        } else if (action.equalsIgnoreCase("viewpost")) {
            if (user != null) {
                String postId = request.getParameter("postId");
                if (postId != null) {
                    Post post = PostDB.getPost(Integer.parseInt(postId));
                    request.setAttribute("post", post);

                    //get all comments by postId to display
                    ArrayList<Comment> comments = CommentDB.getCommentsByPost(post.getId());
                    request.setAttribute("comments", comments);
                    url = "/viewpost.jsp";
                } else {
                    //only display valid posts on homepage
                    ArrayList<Post> posts = PostDB.getPostsByStatus("valid");
                    request.setAttribute("posts", posts);
                    url = "/home.jsp";
                }
            } else {
                url = "/login.jsp";
            }
            //edit action for posts
        } else if (action.equalsIgnoreCase("edit")) {
            if (user != null) {
                String postId = request.getParameter("postId");
                if (postId != null) {
                    Post post = PostDB.getPost(Integer.parseInt(postId));
                    request.setAttribute("post", post);
                    url = "/editpost.jsp";
                }
            } else {
                url = "/login.jsp";
            }
            //report action for posts
        } else if (action.equalsIgnoreCase("report")) {
            if (user != null) {
                String postId = request.getParameter("postId");
                //String question = request.getParameter("question");
                if (postId != null) {
                    Date currDate = new Date();
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    String reportedDate = df.format(currDate);

                    int post_id = Integer.parseInt(postId);
                    Post post = PostDB.getPost(post_id); //get the reported post

                    System.out.println("postid=" + post_id);
                    System.out.println(post.getStatus());

                    post.setStatus("reported");

                    PostDB.updatePost(post_id, post);

                    System.out.println(post.getStatus());

                    request.setAttribute("reported", "true");
                    url = "/home.jsp";
                } else {
                    ArrayList<Post> posts = PostDB.getPostsByStatus("valid");
                    request.setAttribute("posts", posts);
                    url = "/home.jsp";
                }
            } else {
                url = "/login.jsp";
            }
            //admin review page
        } else if (action.equalsIgnoreCase("moderate")) {
            if (user.getType().equals("admin")) {
                ArrayList<Post> posts = PostDB.getPostsByStatus("reported");
                request.setAttribute("posts", posts);
                url = "/reportques.jsp";
            } else {
                url = "/login.jsp";
            }
            //admin approve action
        } else if (action.equalsIgnoreCase(
                "approve")) {
            if (user.getType().equals("admin")) {
                //get, change status and update db
                String postId = request.getParameter("postId");
                int post_id = Integer.parseInt(postId);

                Post post = PostDB.getPost(Integer.parseInt(postId)); //get the reported post

                //if admin approves report, it means post is invalid
                post.setStatus("invalid");

                PostDB.updatePost(post_id, post);

                request.setAttribute("approved", "true");

                ArrayList<Post> posts = PostDB.getPostsByStatus("reported");
                request.setAttribute("posts", posts);
                url = "/reportques.jsp";
            } else {
                url = "/login.jsp";
            }
            //admin disapprove/reject action
        } else if (action.equalsIgnoreCase(
                "disapprove")) {
            if (user.getType().equals("admin")) {
                //get, change status and update db
                String postId = request.getParameter("postId");
                int post_id = Integer.parseInt(postId);

                Post post = PostDB.getPost(Integer.parseInt(postId)); //get the reported post

                //if admin approves report, it means post is valid
                post.setStatus("valid");

                PostDB.updatePost(post_id, post);

                request.setAttribute("disapproved", "true");

                ArrayList<Post> posts = PostDB.getPostsByStatus("reported");
                request.setAttribute("posts", posts);
                url = "/reportques.jsp";
            } else {
                url = "/login.jsp";
            }
        } else if (action.equalsIgnoreCase(
                "add-post")) {
            url = "/addpost.jsp";
            
            //edit option to update posts
        } else if (action.equalsIgnoreCase("update")) {
            if (user != null) {
                String postId = request.getParameter("postId");
                String question = request.getParameter("question");
                String questionDetails = request.getParameter("question_details");

                Date currDate = new Date();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String createdDate = df.format(currDate);

                int post_id = Integer.parseInt(postId);
                Post post = PostDB.getPost(post_id);
                post.setQuestion(question);
                post.setContent(questionDetails);
                post.setModified_date(createdDate);
                post.setUser(user);

                PostDB.updatePost(post_id, post);

                ArrayList<Post> posts = PostDB.getPostsByUser(user.getId());
                request.setAttribute("posts", posts);
                url = "/myposts.jsp";

            } else {
                url = "/login.jsp";
            }
            
            //delete option for posts
        } else if (action.equalsIgnoreCase("delete")) {
            if (user != null) {
                String postId = request.getParameter("postId");

                int post_id = Integer.parseInt(postId);
                Post post = PostDB.getPost(post_id);

                PostDB.deletePost(post_id);

                ArrayList<Post> posts = PostDB.getPostsByUser(user.getId());
                request.setAttribute("deleted", "true");
                request.setAttribute("posts", posts);
                url = "/myposts.jsp";

            } else {
                url = "/login.jsp";
            }
            
            //adding a post
        } else if (action.equalsIgnoreCase(
                "add")) {
            if (user != null) {
                String question = request.getParameter("question");
                String questionDetails = request.getParameter("question_details");
                String categ = request.getParameter("category");

                Date currDate = new Date();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String createdDate = df.format(currDate);

                Post post = new Post();
                post.setQuestion(question);
                post.setContent(questionDetails);
                post.setStatus("valid");
                post.setCreated_date(createdDate);
                post.setModified_date(createdDate);
                post.setUser(user);

                //check if category already exists, or else create one.
                if (CategoryDB.getCategoryByName(categ) != null) {
                    post.setCategory(CategoryDB.getCategoryByName(categ));
                } else {

                    Category category = new Category();
                    category.setName(categ);
                    category.setUser(user);
                    CategoryDB.addCategory(category);
                    System.out.println("category created successfully");
                    category.setId(CategoryDB.getCategoryByName(categ).getId());
                    post.setCategory(category);
                }

                PostDB.addPost(post);

                ArrayList<Post> posts = PostDB.getPostsByStatus("valid");
                request.setAttribute("posts", posts);
                url = "/home.jsp";
            } else {
                url = "/login.jsp";
            }
            
            // commenting action
        } else if (action.equalsIgnoreCase(
                "comment")) {
            if (user != null) {
                String postId = request.getParameter("postId");
                String content = request.getParameter("comment");

                Post post = PostDB.getPost(Integer.parseInt(postId));
                Date currDate = new Date();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String createdDate = df.format(currDate);

                Comment comment = new Comment();
                comment.setContent(content);
                comment.setCreated_date(createdDate);
                comment.setModified_date(createdDate);
                comment.setUser(user); //check later
                comment.setPost(post);
                CommentDB.addComment(comment);

                session.setAttribute("theUser", user);
                request.setAttribute("post", post);

                ArrayList<Comment> comments = CommentDB.getCommentsByPost(post.getId());;
                request.setAttribute("comments", comments);

                url = "/viewpost.jsp";
            } else {
                url = "/login.jsp";
            }
            //goto home page
        } else if (action.equalsIgnoreCase(
                "home")) {
            ArrayList<Post> posts = PostDB.getPostsByStatus("valid");
            request.setAttribute("posts", posts);
            url = "/home.jsp";
            //display all my posts
        } else if (action.equalsIgnoreCase(
                "myposts")) {
            ArrayList<Post> posts = PostDB.getPostsByUser(user.getId());
            request.setAttribute("posts", posts);
            url = "/myposts.jsp";
            //display my profile
        } else if (action.equalsIgnoreCase(
                "profile")) {
            url = "/profile.jsp";
        }        // forward request and response objects to specified URL

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
