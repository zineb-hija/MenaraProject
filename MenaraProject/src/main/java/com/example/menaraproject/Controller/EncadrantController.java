package com.example.menaraproject.Controller;

import com.example.menaraproject.Model.Encadrant;
import com.example.menaraproject.Model.Projet;
import com.example.menaraproject.Model.Tache;
import com.example.menaraproject.Service.EncadrantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/encadrants")
public class EncadrantController {

    @Autowired
    private EncadrantService encadrantService;
    @GetMapping
    public List<Encadrant> getAllEncadrants() {
        return encadrantService.getAllEncadrants();
    }

    @GetMapping("/{id}")
    public Encadrant getEncadrantById(@PathVariable Long id) {
        return encadrantService.getEncadrantById(id);
    }

    @PostMapping
    public Encadrant createEncadrant(@RequestBody Encadrant encadrant) {
        return encadrantService.createEncadrant(encadrant);
    }

    @PutMapping("/{id}")
    public Encadrant updateEncadrant(@PathVariable Long id, @RequestBody Encadrant encadrantDetails) {
        return encadrantService.updateEncadrant(id, encadrantDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteEncadrant(@PathVariable Long id) {
        encadrantService.deleteEncadrant(id);
    }
}


