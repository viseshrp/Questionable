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
public class Post implements Serializable {
    
//    id     INT(8) NOT NULL AUTO_INCREMENT,
//question   VARCHAR(255) NOT NULL,
//content   text NOT NULL,
//user_id		INT(8) NOT NULL,
//category_id INT(8) NOT NULL,
//created_date  VARCHAR(255) NOT NULL,
//modified_date  VARCHAR(255) NOT NULL,
//PRIMARY KEY (id),
//CONSTRAINT fk_user_post FOREIGN KEY (user_id)
//  REFERENCES user(id)
//  ON DELETE CASCADE
//  ON UPDATE CASCADE,
//  CONSTRAINT fk_category_post FOREIGN KEY (category_id)
//  REFERENCES category(id)
    
    private int id;
    private String question;
    private String content;
    private String created_date;
    private String modified_date;
    
    private User user;
    private Category category;

    public Post() {
    }

    public Post(int id, String question, String content, String created_date, String modified_date, User user, Category category) {
        this.id = id;
        this.question = question;
        this.content = content;
        this.created_date = created_date;
        this.modified_date = modified_date;
        this.user = user;
        this.category = category;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getModified_date() {
        return modified_date;
    }

    public void setModified_date(String modified_date) {
        this.modified_date = modified_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    
    
}
