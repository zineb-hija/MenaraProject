package com.example.menaraproject.Service;

import com.example.menaraproject.Model.Encadrant;
import com.example.menaraproject.Model.RH;
import com.example.menaraproject.Model.Stagiaire;
import com.example.menaraproject.Repository.EncadrantRepo;
import com.example.menaraproject.Repository.RHRepo;
import com.example.menaraproject.Repository.StagiaireRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RHService {

    @Autowired
    private EncadrantRepo encadrantRepo;

    @Autowired
    private StagiaireRepo stagiaireRepo;

    @Autowired
    private RHRepo rhRepo;

    public RH ajouterRH(RH rh) {
        return rhRepo.save(rh);
    }

    public Encadrant ajouterEncadrant(Encadrant encadrant) {
        return encadrantRepo.save(encadrant);
    }

    public void supprimerEncadrant(Long id) {
        encadrantRepo.deleteById(id);
    }

    public Stagiaire ajouterStagiaire(Stagiaire stagiaire) {
        return stagiaireRepo.save(stagiaire);
    }

    public void supprimerStagiaire(Long id) {
        stagiaireRepo.deleteById(id);
    }

    public List<Stagiaire> listerStagiaires() {
        return stagiaireRepo.findAll();
    }
}
