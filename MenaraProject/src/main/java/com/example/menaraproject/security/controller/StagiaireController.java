package com.example.menaraproject.security.controller;

import com.example.menaraproject.Model.Projet;
import com.example.menaraproject.Model.Tache;
import com.example.menaraproject.security.model.Encadrant;
import com.example.menaraproject.security.service.StagiaireService;
import com.example.menaraproject.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/stagiaire")
@CrossOrigin("*")
public class StagiaireController {

    @Autowired
    StagiaireService stagiaireService;

    @GetMapping("/taches/{stagiaireId}")
    public Set<Tache> getTaches(@PathVariable Long stagiaireId) {
        return stagiaireService.getTaches(stagiaireId);
    }

    @GetMapping("/encadrant/{stagiaireId}")
    public Encadrant getEncadrant(@PathVariable Long stagiaireId) {
        return stagiaireService.getEncadrant(stagiaireId);
    }

    @GetMapping("/projet/{stagiaireId}")
    public Projet getProjet(@PathVariable Long stagiaireId) {
        return stagiaireService.getProjet(stagiaireId);
    }

    @PutMapping("/validateTache/{stagiaireId}/{tacheId}")
    public Tache validateTache(@PathVariable Long stagiaireId, @PathVariable Long tacheId) {
        return stagiaireService.validateTache(stagiaireId, tacheId);
    }


}
