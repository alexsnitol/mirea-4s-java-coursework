package com.ineri.ineri_lk.service.impl;

import com.ineri.ineri_lk.model.User;
import com.ineri.ineri_lk.repository.RoleRepository;
import com.ineri.ineri_lk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Kozlov Alexander
 */

@Service("userService")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User getUserByUsername(String login) {
        return userRepository.findByUsername(login).orElse(null);
    }

    public void saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername()).orElse(null);

        if (userFromDB == null) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.getRoles().add(roleRepository.findRoleById(2L));
            userRepository.save(user);
        }
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




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElse(null);
    }
}
