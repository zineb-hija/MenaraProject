package com.example.menaraproject.security.service;

import com.example.menaraproject.security.jwt.JwtResponse;
import com.example.menaraproject.security.jwt.JwtUtil;
import com.example.menaraproject.security.model.*;
import com.example.menaraproject.security.repository.UserDao;
import com.example.menaraproject.security.service.RoleService;
import com.example.menaraproject.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class RhService implements UserService {

    private UserDao userDao;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


@Override
public JwtResponse signIn(User user) {
    final Authentication authentication;
    try {
        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    } catch (BadCredentialsException e) {
        throw new BadCredentialsException("bad creditiel for username " + user.getUsername());
    }
    SecurityContextHolder.getContext().setAuthentication(authentication);
    User loadUserByUsername = loadUserByUsername(user.getUsername());
    String token = jwtUtil.generateToken(loadUserByUsername);
    JwtResponse response = new JwtResponse(token);
    return response;
}

    // Save Admin
    @Override
    public User save(User admin) {
        if (userDao.findByUsername(admin.getUsername()) != null) return null;

        Role role = roleService.findByAuthority("RH");
        if (role == null) role = new Role("RH");

        Collection<Role> roles = new ArrayList<>();
        roles.add(role);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setAuthorities(roles);
        roleService.save(role);
        userDao.save(admin);
        return admin;
    }
    // End Save Admin

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user " + username + " not found");
        }
        return user;
    }

    @Override
    public Collection<User> findByRole(String role) {
        return userDao.findByRole(role);
    }

    @Override
    @Transactional
    public int deleteByUsername(String username) {
        User loadedUser = userDao.findByUsername(username);
        if (loadedUser == null) {
            return -1;
        } else {
            userDao.delete(loadedUser);
            return 1;
        }
    }



    // Add and Get Methods for Encadrants and Stagiaires
    public Encadrant addEncadrantToRH(Long rhId, Encadrant encadrant) {
    RH rh = (RH) userDao.findById(rhId).orElse(null);
    if (rh != null) {
        Role role = roleService.findByAuthority("ENCADRANT");
        if (role == null) {
            role = new Role("ENCADRANT");
            roleService.save(role);
        }
        Collection<Role> roles = new ArrayList<>();
        roles.add(role);
        encadrant.setAuthorities(roles);
        encadrant.setRh(rh);
        encadrant.setPassword(passwordEncoder.encode(encadrant.getPassword()));
        return   userDao.save(encadrant);

    }
    return null;
}

public Stagiaire addStagiaireToRH(Long rhId, Long encadrantId, Stagiaire stagiaire) {
    RH rh = (RH) userDao.findById(rhId).orElse(null);
    Encadrant encadrant = (Encadrant) userDao.findById(encadrantId).orElse(null);
    if (rh != null && encadrant != null) {
        Role role = roleService.findByAuthority("STAGIAIRE");
        if (role == null) {
            role = new Role("STAGIAIRE");
            roleService.save(role);
        }
        Collection<Role> roles = new ArrayList<>();
        roles.add(role);
        stagiaire.setAuthorities(roles);
        stagiaire.setRh(rh);
        stagiaire.setEncadrant(encadrant); // Assign the Stagiaire to the Encadrant
        stagiaire.setPassword(passwordEncoder.encode(stagiaire.getPassword()));  // Ensure BCrypt encoding


        return userDao.save(stagiaire); // Save the Stagiaire in the UserDao repository
    }
    return null;
}


    // Delete Methods for Encadrants and Stagiaires
    public int deleteStagiaireFromRH(Long rhId, String stagiaireUsename) {
        RH rh = (RH) userDao.findById(rhId).orElse(null);
        Stagiaire stagiaire = (Stagiaire) userDao.findByUsername(stagiaireUsename);
        if (rh != null && stagiaire != null) {
            rh.getStagiaires().remove(stagiaire);
            userDao.delete(stagiaire); // Delete the Stagiaire from the UserDao repository
            return 1;
        }
        return -1;
    }

    public int deleteEncadrantFromRH(Long rhId, String encadrantUsername) {
    RH rh = (RH) userDao.findById(rhId).orElse(null);
    Encadrant encadrant = (Encadrant) userDao.findByUsername(encadrantUsername);
    if (rh != null && encadrant != null) {
        rh.getEncadrants().remove(encadrant);
        userDao.delete(encadrant); // Delete the Encadrant from the UserDao repository
        return 1;
    }
    return -1;
}


    // Get Methods for Encadrants and Stagiaires



    public Encadrant updateEncadrantInRH(Long rhId, String encadrantUsername,Encadrant updatedEncadrant ) {
        return userDao.findById(rhId)
                .map(rh -> {
                    Encadrant encadrant = (Encadrant) userDao.findByUsername(encadrantUsername);
                    if (encadrant != null) {
                        encadrant.setNom(updatedEncadrant.getNom());
                        encadrant.setPrenom(updatedEncadrant.getPrenom());
                        encadrant.setEmail(updatedEncadrant.getEmail());
                        encadrant.setNom(updatedEncadrant.getNom());
                        encadrant.setPrenom(updatedEncadrant.getPrenom());
                        encadrant.setDatenaissance(updatedEncadrant.getDatenaissance());
                        encadrant.setProjets(updatedEncadrant.getProjets());
                        encadrant.setStagiaires(updatedEncadrant.getStagiaires());
                        encadrant.setTaches(updatedEncadrant.getTaches());
                        encadrant.setPassword(passwordEncoder.encode(updatedEncadrant.getPassword())); // Ensure BCrypt encoding
                         // Save the updated Encadrant in the UserDao repository
                    }
                    return userDao.save(encadrant);
                })
                .orElse(null);
    }




    public Stagiaire updateStagiaireInRH(Long rhId, String stagiaireUsername, Stagiaire updatedStagiaire) {
        return userDao.findById(rhId)
                .map(rh -> {
                    Stagiaire stagiaire = (Stagiaire) userDao.findByUsername(stagiaireUsername);
                    if (stagiaire != null) {
                        stagiaire.setNom(updatedStagiaire.getNom());
                        stagiaire.setPrenom(updatedStagiaire.getPrenom());
                        stagiaire.setEmail(updatedStagiaire.getEmail());
                        stagiaire.setDatenaissance(updatedStagiaire.getDatenaissance());
                        stagiaire.setEcole(updatedStagiaire.getEcole());
                        stagiaire.setStatut(updatedStagiaire.getStatut());
                        //stagiaire.setTaches(updatedStagiaire.getTaches());
                        stagiaire.setEncadrant(updatedStagiaire.getEncadrant());
                        stagiaire.setPassword(passwordEncoder.encode(updatedStagiaire.getPassword()));  // Ensure BCrypt encoding
                         // Save the updated Stagiaire in the UserDao repository
                    }
                    return userDao.save(stagiaire);
                })
                .orElse(null);
    }
    public Set<Encadrant> getEncadrantsOfRH(Long rhId) {
        RH rh = (RH) userDao.findById(rhId).orElse(null);
        if (rh != null) {
            return rh.getEncadrants();
        }
        return null;
    }

    public Set<Stagiaire> getStagiairesOfRH(Long rhId) {
        RH rh = (RH) userDao.findById(rhId).orElse(null);
        if (rh != null) {
            return rh.getStagiaires();
        }
        return null;
    }


}
