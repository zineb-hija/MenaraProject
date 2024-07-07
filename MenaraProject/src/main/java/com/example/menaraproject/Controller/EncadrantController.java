package com.example.menaraproject.Controller;

import com.example.menaraproject.Model.Projet;
import com.example.menaraproject.Model.Tache;
import com.example.menaraproject.Service.EncadrantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/encadrant")
public class EncadrantController {

    @Autowired
    private EncadrantService encadrantService;

    @PostMapping("/projet")
    public Projet ajouterProjet(@RequestBody Projet projet) {
        return encadrantService.ajouterProjet(projet);
    }

    @PostMapping("/tache")
    public Tache ajouterTache(@RequestBody Tache tache) {
        return encadrantService.ajouterTache(tache);
    }

    @GetMapping("/projets")
    public List<Projet> listerProjets() {
        return encadrantService.listerProjets();
    }

    @GetMapping("/taches")
    public List<Tache> listerTaches() {
        return encadrantService.listerTaches();
    }
}
