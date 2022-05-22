package com.ineri.ineri_lk.repository;

import com.ineri.ineri_lk.model.ERole;
import com.ineri.ineri_lk.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Kozlov Alexander
 * @version 1.0
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}