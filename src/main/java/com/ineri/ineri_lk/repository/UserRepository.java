package com.ineri.ineri_lk.repository;

import com.ineri.ineri_lk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Kozlov Alexander
 * @version 1.0
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String username);
    Boolean existsByLogin(String login);
    Boolean existsByEmail(String email);
    Boolean existsByPhoneNumber(String phoneNumber);
}