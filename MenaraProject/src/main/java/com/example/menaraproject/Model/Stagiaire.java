package com.example.menaraproject.Model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Stagiaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String ecole;
    private String statut;

    @ManyToOne
    @JoinColumn(name = "encadrant_id")
    private Encadrant encadrant;

    @ManyToOne
    @JoinColumn(name = "rh_id")
    private RH rh;
    @OneToMany(mappedBy = "stagiaire")
    private Set<Tache> taches;

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

    public String getEcole() {
        return ecole;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Encadrant getEncadrant() {
        return encadrant;
    }

    public void setEncadrant(Encadrant encadrant) {
        this.encadrant = encadrant;
    }

    public RH getRh() {
        return rh;
    }

    public void setRh(RH rh) {
        this.rh = rh;
    }

    public Set<Tache> getTaches() {
        return taches;
    }

    public void setTaches(Set<Tache> taches) {
        this.taches = taches;
    }
}
