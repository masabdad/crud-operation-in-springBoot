package com.myproject.journalApp.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import org.springframework.stereotype.Indexed;

import java.util.ArrayList;
import java.util.List;


@Entity(name = "users")
@Data
public class User {
    @JoinColumn(name = "journal_Entry")

    @Id
    private String id;

    private String userName;

    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public  String getUserName() {
        return userName;
    }

    public void setUserName( String userName) {
        this.userName = userName;
    }

    public  String getPassword() {
        return password;
    }

    public void setPassword( String password) {
        this.password = password;
    }
}
