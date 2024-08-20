package com.NewsTok.User.Models;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="users")

public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserId;

    private String Name;
    
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private Date createdAt;
    private String NewsInterest;

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public String getNewsInterest() {
        return NewsInterest;
    }

    public void setNewsInterest(String newsInterest) {
        NewsInterest = newsInterest;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
