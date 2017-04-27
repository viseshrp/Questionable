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
public class Category implements Serializable {
    
//CREATE TABLE category (
//id     INT(8) NOT NULL AUTO_INCREMENT,
//name   VARCHAR(30) NOT NULL,
//user_id     INT(8) NOT NULL,
//PRIMARY KEY (id),
//CONSTRAINT fk_user FOREIGN KEY (user_id)
//  REFERENCES user(id)
//  ON DELETE CASCADE
//  ON UPDATE CASCADE
//) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

    private int id;
    private String name;
    
    private User user;

    public Category() {
    }

    public Category(int id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    

}
