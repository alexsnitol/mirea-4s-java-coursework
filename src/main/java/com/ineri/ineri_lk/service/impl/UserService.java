package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.ERole;
import com.ineri.ineri_lk.model.Role;
import com.ineri.ineri_lk.model.User;
import com.ineri.ineri_lk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Kozlov Alexander
 * @version 1.0
 *
 * Добавить методы
 */

@Service
public class UserService{

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User getUserByUsername(String login) {
        return userRepository.findByUsername(login).orElse(null);
    }

    public boolean saveUser(User user) {
        Optional<User> userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB.isPresent()) {
            return false;
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public void updateUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

}
