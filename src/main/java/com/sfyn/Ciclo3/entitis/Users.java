package com.sfyn.Ciclo3.entitis;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "email")
    private  String email;

    @Column(name = "image", unique = true)
    private  String image;

    @Column(name = "auth0Id", unique = true)
    private  String auth0Id;

    public Users() {
    }

    public Users(String email, String image, String auth0Id) {
        this.email = email;
        this.image = image;
        this.auth0Id = auth0Id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuth0Id() {
        return auth0Id;
    }

    public void setAuth0Id(String auth0Id) {
        this.auth0Id = auth0Id;
    }
}
