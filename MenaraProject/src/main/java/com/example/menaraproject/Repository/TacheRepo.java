package com.example.menaraproject.Repository;

import com.example.menaraproject.Model.Projet;
import com.example.menaraproject.Model.Tache;
import com.example.menaraproject.security.model.Encadrant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TacheRepo extends JpaRepository<Tache, Long> {

    List<Tache> findByProjet(Projet projet);


    List<Tache> findByProjetId(Long projetId);
}
