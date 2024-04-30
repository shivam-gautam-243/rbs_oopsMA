package com.oop.rbs;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Column
    private Integer userID;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private String email;

    public User() {
    }

    public String getEmail() {
        return email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, Integer userID, String email) {
        this.name = name;
        this.userID = userID;
        this.email = email;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }


}
