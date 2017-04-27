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
public class Comment implements Serializable {
    
  /*  id     INT(8) NOT NULL AUTO_INCREMENT,
content   text NOT NULL,
user_id		INT(8) NOT NULL,
post_id INT(8) NOT NULL,
created_date  VARCHAR(255) NOT NULL,
modified_date  VARCHAR(255) NOT NULL,
PRIMARY KEY (id),
CONSTRAINT fk_user_comment FOREIGN KEY (user_id)
  REFERENCES user(id)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  CONSTRAINT fk_post_comment FOREIGN KEY (post_id)
  REFERENCES post(id)
  ON DELETE CASCADE
  ON UPDATE CASCADE
*/
    
    private int id;
    private String content;
    private String created_date;
    private String modified_date;
    
    private User user;
    private Post post;

    public Comment() {
    }
    
    

    public Comment(int id, String content, String created_date, String modified_date, User user, Post post) {
        this.id = id;
        this.content = content;
        this.created_date = created_date;
        this.modified_date = modified_date;
        this.user = user;
        this.post = post;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
   
}
