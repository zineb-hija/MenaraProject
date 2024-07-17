package com.example.menaraproject.Repository;

import com.example.menaraproject.Model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepo extends JpaRepository<Permission, Long> {
}
