package com.example.menaraproject.security.service;

import com.example.menaraproject.Model.Projet;
import com.example.menaraproject.Model.Tache;
import com.example.menaraproject.Repository.ProjetRepo;
import com.example.menaraproject.Repository.TacheRepo;
import com.example.menaraproject.security.model.Encadrant;
import com.example.menaraproject.security.model.RH;
import com.example.menaraproject.security.model.Stagiaire;
import com.example.menaraproject.security.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EncadrantService {

    @Autowired
    private ProjetRepo projetRepo;

    @Autowired
    private TacheRepo tacheRepo;

    @Autowired
    private UserDao userDao;

    public Projet addProjet(Long encadrantId, Projet projet) {
    Encadrant encadrant = (Encadrant) userDao.findById(encadrantId).orElse(null);
        if (encadrant != null) {
            projet.setEncadrant(encadrant);

            return projetRepo.save(projet);
        }
        return null;
    }

    public Tache addTacheToProjet(Long encadrantId, Long projetId, Long stagiaireId, Tache tache) {
        Encadrant encadrant = (Encadrant) userDao.findById(encadrantId).orElseThrow(() -> new RuntimeException("Encadrant not found"));
        Projet projet = projetRepo.findById(projetId).orElseThrow(() -> new RuntimeException("Projet not found"));
        Stagiaire stagiaire = (Stagiaire) userDao.findById(stagiaireId).orElseThrow(() -> new RuntimeException("Staiaire not found"));

        if (!projet.getEncadrant().equals(encadrant)) {
            throw new RuntimeException("Encadrant does not have permission to add Tache to this Projet");
        }

        tache.setEncadrant(encadrant);
        tache.setProjet(projet);
        tache.setStagiaire(stagiaire); // Assign the Tache to the Stagiaire
        return tacheRepo.save(tache);
    }
    public int deleteProjet(Long encadrantId, Long projetId) {
        Encadrant encadrant = (Encadrant) userDao.findById(encadrantId).orElse(null);
        Projet projet = projetRepo.findById(projetId).orElse(null);
        if (encadrant == null || projet == null) {
            throw new RuntimeException("Encadrant or Projet not found");
        }
        if (!projet.getEncadrant().equals(encadrant)) {
            throw new RuntimeException("Encadrant does not have permission to delete this Projet");
        }
        // Delete all tasks associated with the project
        List<Tache> taches = tacheRepo.findByProjetId(projetId);
        for (Tache tache : taches) {
            tacheRepo.delete(tache);
        }

        projetRepo.delete(projet);
        return 1;
    }

    public int deleteTache(Long encadrantId, Long projetId, Long tacheId) {
        Encadrant encadrant = (Encadrant) userDao.findById(encadrantId).orElse(null);
        Projet projet = projetRepo.findById(projetId).orElse(null);
        Tache tache = tacheRepo.findById(tacheId).orElse(null);

        if (encadrant == null || projet == null || tache == null) {
            throw new RuntimeException("Encadrant, Projet, or Tache not found");
        }

        if (!projet.getEncadrant().equals(encadrant)) {
            throw new RuntimeException("Encadrant does not have permission to delete tasks in this Projet");
        }

        if (!tache.getProjet().equals(projet)) {
            throw new RuntimeException("Tache does not belong to the specified Projet");
        }

        tacheRepo.delete(tache);
        return 1;
    }
    public Projet updateProjet(Long encadrantId, Long projetId, Projet updatedProjet) {
        return userDao.findById(encadrantId)
                .map(encadrant -> {
                    Projet projet = projetRepo.findById(projetId).orElse(null);
                    if (projet != null && projet.getEncadrant().equals(encadrant)) {
                        projet.setTitre(updatedProjet.getTitre());
                        projet.setDescription(updatedProjet.getDescription());
                        projet.setDateDebut(updatedProjet.getDateDebut());
                        projet.setDateFin(updatedProjet.getDateFin());
                        projet.setTaches(updatedProjet.getTaches());
                        projetRepo.save(projet); // Save the updated Projet in the Projet repository
                    } else {
                        throw new RuntimeException("Encadrant does not have permission to update this Projet or Projet not found");
                    }
                    return projet;
                })
                .orElseThrow(() -> new RuntimeException("Encadrant not found"));
    }


    public Tache updateTache(Long encadrantId, Long projetId, Long tacheId, Tache updatedTache) {
    return userDao.findById(encadrantId)
            .map(encadrant -> {
                Projet projet = projetRepo.findById(projetId).orElse(null);
                Tache tache = null; // Declare tache here
                if (projet != null && projet.getEncadrant() != null && projet.getEncadrant().equals(encadrant)) {
                    tache = tacheRepo.findById(tacheId).orElse(null); // Assign tache here
                    if (tache != null && tache.getProjet().equals(projet)) {
                        tache.setTitre(updatedTache.getTitre());
                        tache.setDescription(updatedTache.getDescription());
                        tache.setDateLimite(updatedTache.getDateLimite());
                        tache.setStatut(updatedTache.getStatut());
                        // Do not set projet, stagiaire, and encadrant from updatedTache
                        tacheRepo.save(tache); // Save the updated Tache in the Tache repository
                    } else {
                        throw new RuntimeException("Tache does not belong to the specified Projet or Tache not found");
                    }
                } else {
                    throw new RuntimeException("Encadrant does not have permission to update tasks in this Projet or Projet not found");
                }
                return tache; // Now tache is accessible here
            })
            .orElseThrow(() -> new RuntimeException("Encadrant not found"));
}

    public Set<Stagiaire> getStagiaires(Long encadrantId) {
        Encadrant encadrant = (Encadrant) userDao.findById(encadrantId).orElse(null);
        if (encadrant != null) {
            return encadrant.getStagiaires();
        }return null;}

    public List<Projet> getProjets(Long encadrantId) {
        Encadrant encadrant = (Encadrant) userDao.findById(encadrantId).orElse(null);
        if (encadrant == null) {
            throw new RuntimeException("Encadrant not found");
        }
        return projetRepo.findByEncadrant(encadrant);
    }

    public List<Tache> getTachesOfProjet(Long encadrantId, Long projetId) {
        Encadrant encadrant = (Encadrant) userDao.findById(encadrantId).orElse(null);
        Projet projet = projetRepo.findById(projetId).orElse(null);
        if (!projet.getEncadrant().equals(encadrant)) {
            throw new RuntimeException("Encadrant does not have permission to view Tache of this Projet");
        }
        return tacheRepo.findByProjet(projet);}
    
}