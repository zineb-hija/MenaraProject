package com.example.menaraproject.Repository;

import com.example.menaraproject.Model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacheRepo extends JpaRepository<Tache, Long> {
}
