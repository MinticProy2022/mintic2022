package com.sfyn.Ciclo3.entitis;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "auth0Id", unique = true)
    private String auth0Id;

    public User() {
    }

    public User(String email, String auth0Id) {
        this.email = email;
        this.auth0Id = auth0Id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuth0Id() {
        return auth0Id;
    }

    public void setAuth0Id(String auth0Id) {
        this.auth0Id = auth0Id;
    }
}
