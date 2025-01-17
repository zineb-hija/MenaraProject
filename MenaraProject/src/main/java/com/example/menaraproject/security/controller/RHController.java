package com.example.menaraproject.security.controller;


import com.example.menaraproject.security.jwt.JwtResponse;
import com.example.menaraproject.security.model.Encadrant;
import com.example.menaraproject.security.model.RH;
import com.example.menaraproject.security.model.Stagiaire;
import com.example.menaraproject.security.model.User;
import com.example.menaraproject.security.service.UserService;
import com.example.menaraproject.security.service.RhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/rh")
@CrossOrigin("*")
public class RHController {
    @Qualifier("rhService")
    @Autowired
    UserService userService;

    @PostMapping("/")
    public User save(@RequestBody RH rh) {
        return userService.save(rh);
    }

    @GetMapping("/")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/username/{username}")
    public UserDetails loadUserByUsername(@PathVariable String username) throws UsernameNotFoundException {
        return userService.loadUserByUsername(username);
    }
    @GetMapping("/role/{role}")
    public Collection<User> findByRole(@PathVariable String role) {
        return userService.findByRole(role);
    }

    @PostMapping("/encadrant/{rhId}")
    public User addEncadrant(@PathVariable Long rhId, @RequestBody Encadrant encadrant) {
        return ((RhService) userService).addEncadrantToRH(rhId, encadrant);
    }


   @PostMapping("/stagiaire/{rhId}")
    public User addStagiaire(@PathVariable Long rhId, @RequestBody Stagiaire stagiaire) {
        return ((RhService) userService).addStagiaireToRH(rhId, stagiaire);
    }


    @DeleteMapping("/deleteencadrant/{rhid}/{encadrantusername}")
    public int deleteEncadrant(@PathVariable Long rhid, @PathVariable String encadrantusername) {
        return ((RhService) userService).deleteEncadrantFromRH(rhid, encadrantusername);
    }

    @DeleteMapping("/deletestagiaire/{rhid}/{stagiaireusername}")
    public int deleteStagiaire(@PathVariable Long rhid, @PathVariable String stagiaireusername) {
        return ((RhService) userService).deleteStagiaireFromRH(rhid, stagiaireusername);
    }


    @PutMapping("/updateencadrant/{rhid}/{encadrantusername}")
    public User updateEncadrant(@PathVariable Long rhid, @PathVariable String encadrantusername, @RequestBody Encadrant encadrant) {
        return ((RhService) userService).updateEncadrantInRH(rhid, encadrantusername, encadrant);
    }
    @PutMapping("/updatestagiaire/{rhid}/{stagiaireusername}")
    public User updateStagiaire(@PathVariable Long rhid, @PathVariable String stagiaireusername, @RequestBody Stagiaire stagiaire) {
        return ((RhService) userService).updateStagiaireInRH(rhid, stagiaireusername, stagiaire);
    }



   /* @PutMapping("/{id}")
    public RH updateRH(@PathVariable Long id, @RequestBody RH rhDetails) {
        return rhService.updateRH(id, rhDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteRH(@PathVariable Long id) {
        rhService.deleteRH(id);
    }*/
   @PostMapping("/login")
   public JwtResponse signIn(@RequestBody User user) {
       return userService.signIn(user);
   }
}
