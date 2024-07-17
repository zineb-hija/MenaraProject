package com.example.menaraproject.Model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class User {
        @Id
        private String id;
        private String nom;
        private String prenom;
        private String email;
        private String password;

        @ManyToOne
        @JoinColumn(name = "role_id")
        private Role role;

        @ManyToMany
        @JoinTable(
                name = "user_permissions",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "permission_id"))
        private Set<Permission> permissions;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getNom() {
                return nom;
        }

        public void setNom(String nom) {
                this.nom = nom;
        }

        public String getPrenom() {
                return prenom;
        }

        public void setPrenom(String prenom) {
                this.prenom = prenom;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public Role getRole() {
                return role;
        }

        public void setRole(Role role) {
                this.role = role;
        }

        public Set<Permission> getPermissions() {
                return permissions;
        }

        public void setPermissions(Set<Permission> permissions) {
                this.permissions = permissions;
        }
}


