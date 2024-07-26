package com.example.menaraproject.security.controller;

import com.example.menaraproject.Model.Projet;
import com.example.menaraproject.Model.Tache;
import com.example.menaraproject.security.model.Encadrant;
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

@RestController
@RequestMapping("/api/encadrant")
@CrossOrigin("*")
public class EncadrantController {

    @Autowired
    private UserService userService;

    @Autowired
    private EncadrantService encadrantService;

    @GetMapping("/role/{role}")
    public Collection<User> findByRole(@PathVariable String role) {
        return userService.findByRole(role);
    }

    @GetMapping("/username/{username}")
    public UserDetails loadUserByUsername(@PathVariable String username) throws UsernameNotFoundException {
        return userService.loadUserByUsername(username);
    }

    @GetMapping("/projets/{encadrantId}")
    public List<Projet> getProjets(@PathVariable Long encadrantId) {
        return encadrantService.getProjets(encadrantId);
    }

    @PostMapping("/projet/{encadrantId}")
    public Encadrant addProjet(@PathVariable Long encadrantId, @RequestBody Projet projet) {
        return encadrantService.addProjet(encadrantId, projet);
    }

    @PostMapping("/tache/{encadrantId}")
    public Tache addTache(@PathVariable Long encadrantId, @RequestBody Tache tache) {
        return encadrantService.addTacheToProjet(encadrantId, tache.getProjet(), tache);
    }

    @PostMapping("/projet/{projetId}/tache/{encadrantId}")
    public Tache addTacheToProjet(@PathVariable Long encadrantId, @RequestBody Projet projetId, @RequestBody Tache tache) {
        return encadrantService.addTacheToProjet(encadrantId, projetId, tache);
    }
}