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
    private RHRepo rhRepo;
    public List<RH> getAllRHs() {
        return rhRepo.findAll();
    }

    public RH getRHById(Long id) {
        return rhRepo.findById(id).orElse(null);
    }

    public RH createRH(RH rh) {
        return rhRepo.save(rh);
    }

    public RH updateRH(Long id, RH rhDetails) {
        RH rh = rhRepo.findById(id).orElse(null);
        if (rh != null) {
            rh.setNom(rhDetails.getNom());
            rh.setPrenom(rhDetails.getPrenom());
            rh.setEmail(rhDetails.getEmail());
            rh.setPassword(rhDetails.getPassword());
            return rhRepo.save(rh);
        }
        return null;
    }

    public void deleteRH(Long id) {
        rhRepo.deleteById(id);
    }


}
