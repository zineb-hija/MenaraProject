package com.example.menaraproject.Repository;

import com.example.menaraproject.Model.Projet;
import com.example.menaraproject.security.model.Encadrant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProjetRepo extends JpaRepository<Projet, Long> {
    Projet findByTitreAndEncadrant(String titre, Encadrant encadrant);

    List<Projet> findByEncadrant(Encadrant encadrant);
}
