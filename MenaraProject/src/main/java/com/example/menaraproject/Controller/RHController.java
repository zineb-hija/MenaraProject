package com.example.menaraproject.Controller;


import com.example.menaraproject.Model.Encadrant;
import com.example.menaraproject.Model.RH;
import com.example.menaraproject.Model.Stagiaire;
import com.example.menaraproject.Service.RHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rhs")
public class RHController {
    @Autowired
    private RHService rhService;

    @GetMapping
    public List<RH> getAllRHs() {
        return rhService.getAllRHs();
    }

    @GetMapping("/{id}")
    public RH getRHById(@PathVariable Long id) {
        return rhService.getRHById(id);
    }

    @PostMapping
    public RH createRH(@RequestBody RH rh) {
        return rhService.createRH(rh);
    }

    @PutMapping("/{id}")
    public RH updateRH(@PathVariable Long id, @RequestBody RH rhDetails) {
        return rhService.updateRH(id, rhDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteRH(@PathVariable Long id) {
        rhService.deleteRH(id);
    }
}
