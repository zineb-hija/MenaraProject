package com.example.menaraproject.security.service;

import com.example.menaraproject.Model.Projet;
import com.example.menaraproject.Model.Tache;
import com.example.menaraproject.Repository.ProjetRepo;
import com.example.menaraproject.Repository.TacheRepo;
import com.example.menaraproject.security.model.Encadrant;
import com.example.menaraproject.security.model.RH;
import com.example.menaraproject.security.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncadrantService {

    @Autowired
    private ProjetRepo projetRepo;

    @Autowired
    private TacheRepo tacheRepo;

    @Autowired
    private UserDao userDao;

    public Encadrant addProjet(Long encadrantId, Projet projet) {
    Encadrant encadrant = (Encadrant) userDao.findById(encadrantId).orElse(null);
        if (encadrant != null) {
            projet.setEncadrant(encadrant);
            encadrant.addProjet(projet);
            projetRepo.save(projet);
            return (Encadrant) userDao.save(encadrant);
        }
        return null;
    }




            public Tache addTacheToProjet(Long encadrantId, Projet projet, Tache tache) {
        Encadrant encadrant = (Encadrant) userDao.findById(encadrantId).orElse(null);
        if (encadrant == null) {
            throw new RuntimeException("Encadrant not found");
        }
        if (projet.getEncadrant().equals(encadrant)) {
            tache.setProjet(projet);
            return tacheRepo.save(tache);
        } else {
            throw new RuntimeException("Encadrant does not have permission to add Tache to this Projet");
        }
    }

    public List<Projet> getProjets(Long encadrantId) {
        Encadrant encadrant = (Encadrant) userDao.findById(encadrantId).orElse(null);
        if (encadrant == null) {
            throw new RuntimeException("Encadrant not found");
        }
        return projetRepo.findByEncadrant(encadrant);
    }
}