package com.example.menaraproject.Model;



import jakarta.persistence.*;

import java.util.List;

@Entity
public class RH {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;

    @OneToMany(mappedBy = "rh")
    private List<Encadrant> encadrants;

    @OneToMany(mappedBy = "rh")
    private List<Stagiaire> stagiaires;

    // Getters and Setters

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

    public List<Encadrant> getEncadrants() {
        return encadrants;
    }

    public void setEncadrants(List<Encadrant> encadrants) {
        this.encadrants = encadrants;
    }

    public List<Stagiaire> getStagiaires() {
        return stagiaires;
    }

    public void setStagiaires(List<Stagiaire> stagiaires) {
        this.stagiaires = stagiaires;
    }
}

