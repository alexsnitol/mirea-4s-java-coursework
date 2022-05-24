package com.ineri.ineri_lk.repository;

import com.ineri.ineri_lk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Kozlov Alexander
 * @version 1.0
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String username);
    User findByEmail(String email);
    User findByUsername(String username);
    List<User> findAll();

    Boolean existsByLogin(String login);
    Boolean existsByEmail(String email);
    Boolean existsByPhoneNumber(String phoneNumber);
}