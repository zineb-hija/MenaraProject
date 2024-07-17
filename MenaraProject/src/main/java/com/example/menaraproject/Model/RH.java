package com.example.menaraproject.Model;



import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class RH {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;

    @OneToMany(mappedBy = "rh")
    private Set<Encadrant> encadrants;

    @OneToMany(mappedBy = "rh")
    private Set<Stagiaire> stagiaires;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Encadrant> getEncadrants() {
        return encadrants;
    }

    public void setEncadrants(Set<Encadrant> encadrants) {
        this.encadrants = encadrants;
    }

    public Set<Stagiaire> getStagiaires() {
        return stagiaires;
    }

    public void setStagiaires(Set<Stagiaire> stagiaires) {
        this.stagiaires = stagiaires;
    }
}

