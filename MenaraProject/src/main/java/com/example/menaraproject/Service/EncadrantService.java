package com.example.menaraproject.Service;

import com.example.menaraproject.Model.Projet;
import com.example.menaraproject.Model.Tache;
import com.example.menaraproject.Repository.ProjetRepo;
import com.example.menaraproject.Repository.TacheRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncadrantService {
    @Autowired
    private ProjetRepo projetRepo;

    @Autowired
    private TacheRepo tacheRepo;

    public Projet ajouterProjet(Projet projet) {
        return projetRepo.save(projet);
    }

    public Tache ajouterTache(Tache tache) {
        return tacheRepo.save(tache);
    }

    public List<Projet> listerProjets() {
        return projetRepo.findAll();
    }

    public List<Tache> listerTaches() {
        return tacheRepo.findAll();
    }


}
