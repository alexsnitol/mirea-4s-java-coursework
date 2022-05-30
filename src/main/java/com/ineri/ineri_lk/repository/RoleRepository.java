package com.ineri.ineri_lk.repository;

import com.ineri.ineri_lk.model.ERole;
import com.ineri.ineri_lk.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * @author Kozlov Alexander
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(ERole erole);
    Role findRoleByName(String name);
    Role findRoleById(Long id);
}
