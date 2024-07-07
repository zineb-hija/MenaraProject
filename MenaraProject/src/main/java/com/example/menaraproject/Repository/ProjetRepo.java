package com.example.menaraproject.Repository;

import com.example.menaraproject.Model.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetRepo extends JpaRepository<Projet, Long> {
}
