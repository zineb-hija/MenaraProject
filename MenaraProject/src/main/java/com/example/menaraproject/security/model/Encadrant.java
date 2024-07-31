package com.example.menaraproject.security.model;

import com.example.menaraproject.Model.Projet;
import com.example.menaraproject.Model.Tache;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Encadrant extends User{

    public Encadrant() {
        super();
    }



    private Date datenaissance;


    @ManyToOne
    @JoinColumn(name = "rh_id")
    @JsonBackReference
    private RH rh;

    @OneToMany(mappedBy = "encadrant")
    private Set<Stagiaire> stagiaires;

    @OneToMany(mappedBy = "encadrant")
    private Set<Projet> projets;

    @OneToMany(mappedBy = "encadrant")
    private Set<Tache> taches;
    public void addProjet(Projet projet) {
        this.projets.add(projet);
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public RH getRh() {
        return rh;
    }

    public void setRh(RH rh) {
        this.rh = rh;
    }

    public Set<Stagiaire> getStagiaires() {
        return stagiaires;
    }

    public void setStagiaires(Set<Stagiaire> stagiaires) {
        this.stagiaires = stagiaires;
    }

    public Set<Projet> getProjets() {
        return projets;
    }

    public void setProjets(Set<Projet> projets) {
        this.projets = projets;
    }

    public Set<Tache> getTaches() {
        return taches;
    }

    public void setTaches(Set<Tache> taches) {
        this.taches = taches;
    }
}
