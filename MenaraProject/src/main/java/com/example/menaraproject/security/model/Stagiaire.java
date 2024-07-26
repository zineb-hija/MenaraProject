package com.example.menaraproject.security.model;

import com.example.menaraproject.Model.Tache;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class Stagiaire extends User {


    private String ecole;
    private String statut;
    private Date datenaissance;


    @ManyToOne
    @JoinColumn(name = "encadrant_id")
    private Encadrant encadrant;

    @ManyToOne
    @JoinColumn(name = "rh_id")
    private RH rh;
    @OneToMany(mappedBy = "stagiaire")
    private Set<Tache> taches;

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

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
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
