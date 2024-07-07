package com.example.menaraproject.Controller;


import com.example.menaraproject.Model.Encadrant;
import com.example.menaraproject.Model.RH;
import com.example.menaraproject.Model.Stagiaire;
import com.example.menaraproject.Service.RHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rh")
public class RHController {

    @Autowired
    private RHService rhService;

    @PostMapping
    public RH ajouterRH(@RequestBody RH rh) {
        return rhService.ajouterRH(rh);
    }

    @PostMapping("/encadrant")
    public Encadrant ajouterEncadrant(@RequestBody Encadrant encadrant) {
        return rhService.ajouterEncadrant(encadrant);
    }

    @DeleteMapping("/encadrant/{id}")
    public void supprimerEncadrant(@PathVariable Long id) {
        rhService.supprimerEncadrant(id);
    }

    @PostMapping("/stagiaire")
    public Stagiaire ajouterStagiaire(@RequestBody Stagiaire stagiaire) {
        return rhService.ajouterStagiaire(stagiaire);
    }

    @DeleteMapping("/stagiaire/{id}")
    public void supprimerStagiaire(@PathVariable Long id) {
        rhService.supprimerStagiaire(id);
    }

    @GetMapping("/stagiaires")
    public List<Stagiaire> listerStagiaires() {
        return rhService.listerStagiaires();
    }
}
