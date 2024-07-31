package com.example.menaraproject.security.service;

import com.example.menaraproject.Model.Projet;
import com.example.menaraproject.Model.Tache;
import com.example.menaraproject.Repository.TacheRepo;
import com.example.menaraproject.security.model.Encadrant;
import com.example.menaraproject.security.model.Stagiaire;
import com.example.menaraproject.security.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StagiaireService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private TacheRepo tacheRepo;

    public Set<Tache> getTaches(Long stagiaireId) {
        Stagiaire stagiaire = (Stagiaire) userDao.findById(stagiaireId).orElse(null);
        if (stagiaire != null) {
            return stagiaire.getTaches();
        }
        return null;
    }

    public Encadrant getEncadrant(Long StagiaireId) {
        Stagiaire stagiaire = (Stagiaire) userDao.findById(StagiaireId).orElse(null);
        if (stagiaire != null) {
            return stagiaire.getEncadrant();
        }
        return null;}

public Projet getProjet(Long stagiaireId) {
    Stagiaire stagiaire = (Stagiaire) userDao.findById(stagiaireId).orElse(null);
    if (stagiaire != null && !stagiaire.getTaches().isEmpty()) {
        // Get the first Tache from the Set
        Tache firstTache = stagiaire.getTaches().iterator().next();
        // Return the Projet associated with the Tache
        return firstTache.getProjet();
    }
    return null;
}

    public Tache validateTache(Long stagiaireId, Long tacheId) {
        Stagiaire stagiaire = (Stagiaire) userDao.findById(stagiaireId).orElse(null);
        Tache tache = tacheRepo.findById(tacheId).orElse(null);
        if (stagiaire != null && tache != null && tache.getStagiaire().equals(stagiaire)) {
            tache.setStatut("Completed"); // Set the status to "Completed"
            tacheRepo.save(tache); // Save the updated Tache in the Tache repository
            return tache;
        }
        throw new RuntimeException("Tache not found or Stagiaire does not have permission to validate this Tache");
    }
}
