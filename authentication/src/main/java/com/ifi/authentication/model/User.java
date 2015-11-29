package com.ifi.authentication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class User implements Serializable{

    @Id
    private String userName;

    private String nom;

    private String prenom;

    @JsonIgnore
    private String password;

    public User(String userName, String nom, String prenom, String password) {
        this.userName = userName;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
    }

    public User() {
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
