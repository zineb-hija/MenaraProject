package com.example.menaraproject.Controller;

import com.example.menaraproject.Model.Stagiaire;
import com.example.menaraproject.Model.Tache;
import com.example.menaraproject.Repository.StagiaireRepo;
import com.example.menaraproject.Repository.TacheRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StagiaireController {
    @Autowired
    private StagiaireRepo stagiaireRepo;

    @Autowired
    private TacheRepo tacheRepo;

    public Stagiaire modifierStatut(Long id, String statut) {
        Stagiaire stagiaire = stagiaireRepo.findById(id).orElseThrow();
        stagiaire.setStatut(statut);
        return stagiaireRepo.save(stagiaire);
    }

    public Tache validerTache(Long id) {
        Tache tache = tacheRepo.findById(id).orElseThrow();
        tache.setStatut("Terminé");
        return tacheRepo.save(tache);
    }
}
