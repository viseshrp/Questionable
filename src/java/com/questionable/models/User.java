/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.questionable.models;

import java.io.Serializable;

/**
 *
 * @author viseshprasad
 */
public class User implements Serializable {

    private int id; //randomly generated number
    private String user_name; //username
    private String password; //password
    private String email; //email
    private String type; //type = admin or user
    private String reg_date; // registration date


    public User() {
        id = 0;
        user_name = "";
        password = "";
        email = "";
        type = "";
        reg_date = "";
    }

    public User(int user_id, String user_name, String user_password, String email, String type, String reg_date) {
        this.id = user_id;
        this.user_name = user_name;
        this.password = user_password;
        this.email = email;
        this.type = type;
        this.reg_date = reg_date;
    }

    public int getId() {
        return id;
    }

    //TODO: write code to randomly generate later
    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    


}
