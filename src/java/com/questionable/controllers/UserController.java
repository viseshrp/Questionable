/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.questionable.controllers;

import com.questionable.models.Post;
import com.questionable.models.User;
import com.questionable.utility.MailUtils;
import com.questionable.utility.PasswordUtils;
import com.questionable.utility.PostDB;
import com.questionable.utility.UserDB;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *Controller to manage user flow like signup, signin, contact-us, etc.
 *
 * @author viseshprasad
 */
public class UserController extends HttpServlet {

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
        // get current action
        String action = request.getParameter("action");
        User user = (User) session.getAttribute("theUser");
        
        //Check if user is logged in.
        if (action == null) {
            url = "/home.jsp";    // the "main" page
        } else if (action.equalsIgnoreCase("start-login")) { //start login process
            url = "/login.jsp";
        } else if (action.equalsIgnoreCase("start-signup")) { //start signup process
            url = "/signup.jsp";
        } else if (action.equalsIgnoreCase("start-contact")) { //start contact process
            url = "/contact.jsp";
        } else if (action.equalsIgnoreCase("login")) { //attempt login
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            //get user, set him to session and redirect him to
            //the home page.
            User loginUser = UserDB.getUser(email);
            if (loginUser != null) {
                    User userBean = new User(loginUser.getId(),
                            loginUser.getUser_name(),
                            loginUser.getPassword(),
                            loginUser.getEmail(),
                            loginUser.getType(),
                            loginUser.getReg_date());
                    session.setAttribute("theUser", userBean);

                    ArrayList<Post> posts = PostDB.getPostsByStatus("valid");
                    request.setAttribute("posts", posts);

                    url = "/home.jsp";

            } else {
                request.setAttribute("msg", "Not a valid user");
                url = "/login.jsp";
            }
            //user signup flow
        } else if (action.equalsIgnoreCase("create")) {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String type = "user";
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirm_password");
            
            //Validation
            if (username != null && email != null && password != null && confirmPassword != null && password.equals(confirmPassword)) {
                User userBean = new User();
                userBean.setUser_name(username);
                userBean.setEmail(email);
                
                //Password salting and hashing
                try {
                    password = PasswordUtils.hashAndSaltPassword(request.getParameter("password"));
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                userBean.setPassword(password);
                userBean.setType("user");

                Date currDate = new Date();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String reg_date = df.format(currDate);

                userBean.setReg_date(reg_date);

                UserDB.addUser(userBean);

                session.setAttribute("theUser", userBean);
                url = "/home.jsp";
            } else {
                request.setAttribute("msg", "Cannot create the account");
                url = "/signup.jsp";
            }
        } else if (action.equalsIgnoreCase("about")) {
            url = "/about.jsp";
        } else if (action.equalsIgnoreCase("logout")) {
            if (user != null) {
                session.invalidate();
                url = "/home.jsp";
            } else {
                url = "/home.jsp";
            }
            
            /*
            Contact Us option with email functionality.
            Send an email to all the admins.
            */
        } else if (action.equalsIgnoreCase("contact")) {
            
            ArrayList<String> adminEmails = UserDB.getAdminEmail();

            System.out.println("emails: " + adminEmails.toString());
            for (String email : adminEmails) {
                try {
                    System.out.println("useremail: " + user.getEmail());
                    System.out.println("admin: " + email);
                    //Email functionality.
                    MailUtils.sendMail(email, user.getEmail(), "Feedback from " + request.getParameter("email"), request.getParameter("message"), false);
                } catch (MessagingException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            request.setAttribute("contacted", "true");
            url = "/contact.jsp";
        }
        // forward request and response objects to specified URL
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
