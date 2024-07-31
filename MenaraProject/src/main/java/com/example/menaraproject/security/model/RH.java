package com.example.menaraproject.security.model;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class RH extends User {

    public RH() {
        super();
    }
    @OneToMany(mappedBy = "rh")
    @JsonManagedReference
    private Set<Encadrant> encadrants;

    @OneToMany(mappedBy = "rh")
    private Set<Stagiaire> stagiaires;

    public Set<Encadrant> getEncadrants() {
        return encadrants;
    }
    // RH.java
    public void addStagiaire(Stagiaire stagiaire) {
        this.stagiaires.add(stagiaire);
    }
    // RH.java
    public void addEncadrant(Encadrant encadrant) {
        this.encadrants.add(encadrant);
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

