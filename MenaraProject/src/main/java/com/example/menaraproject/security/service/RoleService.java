package com.example.menaraproject.security.service;

import com.example.menaraproject.security.model.Role;
import com.example.menaraproject.security.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public Role save(Role role) {
        Role loadedRole = roleDao.findByAuthority(role.getAuthority());
        if (loadedRole == null) {
            return roleDao.save(role);
        } else {
            return loadedRole;
        }
    }

    public void save(Collection<Role> roles) {
        if (roles != null && !roles.isEmpty()) {
            for (Role role : roles) {
                Role foundedRole = findByAuthority(role.getAuthority());
                if (foundedRole != null) {
                    role.setId(foundedRole.getId());
                } else {
                    roleDao.save(role);
                }
            }
        }
    }

    public Role findByAuthority(String authority) {
        return roleDao.findByAuthority(authority);
    }
}
