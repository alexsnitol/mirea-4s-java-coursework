package com.ineri.ineri_lk.repository;

import com.ineri.ineri_lk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Kozlov Alexander
 * @version 1.0
 */

@Repository
public interface UserRepository extends JpaRepository<Long, User> {
}