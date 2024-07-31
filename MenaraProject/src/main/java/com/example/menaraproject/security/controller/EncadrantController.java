package com.example.menaraproject.security.controller;

import com.example.menaraproject.Model.Projet;
import com.example.menaraproject.Model.Tache;
import com.example.menaraproject.security.model.Encadrant;
import com.example.menaraproject.security.model.Stagiaire;
import com.example.menaraproject.security.model.User;
import com.example.menaraproject.security.service.EncadrantService;
import com.example.menaraproject.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/encadrant")
@CrossOrigin("*")
public class EncadrantController {

    @Autowired
    private UserService userService;

    @Autowired
    private EncadrantService encadrantService;

    @GetMapping("/stagiaires/{encadrantId}")
    public Set<Stagiaire> getStagiaires(@PathVariable Long encadrantId) {
        return encadrantService.getStagiaires(encadrantId);
    }

    @GetMapping("/projets/{encadrantId}")
    public List<Projet> getProjets(@PathVariable Long encadrantId) {
        return encadrantService.getProjets(encadrantId);
    }

    @GetMapping("/taches/{encadrantId}/{projetId}")
    public List<Tache> getTachesOfProjet(@PathVariable Long encadrantId, @PathVariable Long projetId) {
        return encadrantService.getTachesOfProjet(encadrantId, projetId);
    }

    @PostMapping("/addprojet/{encadrantId}")
    public Projet addProjet(@PathVariable Long encadrantId, @RequestBody Projet projet) {
        return encadrantService.addProjet(encadrantId, projet);
    }

    @PostMapping("/addtache/{encadrantId}/{projetId}/{stagiaireId}")
    public Tache addTache(@PathVariable Long encadrantId, @PathVariable Long projetId, @PathVariable Long stagiaireId, @RequestBody Tache tache) {
        return encadrantService.addTacheToProjet(encadrantId, projetId, stagiaireId, tache);
    }
    @DeleteMapping("/deleteprojet/{encadrantId}/{projetId}")
    public int deleteProjet(@PathVariable Long encadrantId, @PathVariable Long projetId) {
        return encadrantService.deleteProjet(encadrantId, projetId);
    }
    @DeleteMapping("/deletetache/{encadrantId}/{projetId}/{tacheId}")
    public int deleteTache(@PathVariable Long encadrantId, @PathVariable Long projetId, @PathVariable Long tacheId) {
        return encadrantService.deleteTache(encadrantId, projetId, tacheId);
    }

    @PutMapping("/updateprojet/{encadrantId}/{projetId}")
    public Projet updateProjet(@PathVariable Long encadrantId, @PathVariable Long projetId, @RequestBody Projet projet) {
        return encadrantService.updateProjet(encadrantId, projetId, projet);
    }

    @PutMapping("/updatetache/{encadrantId}/{projetId}/{tacheId}")
    public Tache updateTache(@PathVariable Long encadrantId, @PathVariable Long projetId, @PathVariable Long tacheId, @RequestBody Tache tache) {
        return encadrantService.updateTache(encadrantId, projetId, tacheId, tache);
    }





    @GetMapping("/role/{role}")
    public Collection<User> findByRole(@PathVariable String role) {
        return userService.findByRole(role);
    }

    @GetMapping("/username/{username}")
    public UserDetails loadUserByUsername(@PathVariable String username) throws UsernameNotFoundException {
        return userService.loadUserByUsername(username);
    }

}