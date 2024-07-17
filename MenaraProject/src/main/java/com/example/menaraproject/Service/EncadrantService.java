package com.example.menaraproject.Service;

import com.example.menaraproject.Model.Encadrant;
import com.example.menaraproject.Model.Projet;
import com.example.menaraproject.Model.Tache;
import com.example.menaraproject.Repository.EncadrantRepo;
import com.example.menaraproject.Repository.ProjetRepo;
import com.example.menaraproject.Repository.TacheRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncadrantService {
    @Autowired
    private EncadrantRepo encadrantRepo;

    public List<Encadrant> getAllEncadrants() {
        return encadrantRepo.findAll();
    }

    public Encadrant getEncadrantById(Long id) {
        return encadrantRepo.findById(id).orElse(null);
    }

    public Encadrant createEncadrant(Encadrant encadrant) {
        return encadrantRepo.save(encadrant);
    }

    public Encadrant updateEncadrant(Long id, Encadrant encadrantDetails) {
        Encadrant encadrant = encadrantRepo.findById(id).orElse(null);
        if (encadrant != null) {
            encadrant.setNom(encadrantDetails.getNom());
            encadrant.setPrenom(encadrantDetails.getPrenom());
            encadrant.setEmail(encadrantDetails.getEmail());
            encadrant.setPassword(encadrantDetails.getPassword());
            return encadrantRepo.save(encadrant);
        }
        return null;
    }

    public void deleteEncadrant(Long id) {
        encadrantRepo.deleteById(id);
    }


}
