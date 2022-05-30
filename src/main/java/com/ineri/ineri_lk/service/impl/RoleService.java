package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.ERole;
import com.ineri.ineri_lk.model.Role;
import com.ineri.ineri_lk.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Kozlov Alexander
 */

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role getByName(ERole eRole) {
        return roleRepository.findByName(eRole);
    }

    public Role getRoleById(Long id) {
        return roleRepository.findRoleById(id);
    }
}
