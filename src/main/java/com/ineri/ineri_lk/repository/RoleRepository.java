package com.ineri.ineri_lk.repository;

import com.ineri.ineri_lk.model.ERole;
import com.ineri.ineri_lk.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Kozlov Alexander
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(ERole erole);
}
