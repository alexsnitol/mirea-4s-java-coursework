package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.ERole;
import com.ineri.ineri_lk.model.Role;
import com.ineri.ineri_lk.model.User;
import com.ineri.ineri_lk.repository.RoleRepository;
import com.ineri.ineri_lk.service.impl.RoleService;
import com.ineri.ineri_lk.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Kozlov Alexander
 */

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @GetMapping("/{username}/users")
    public String findAll(Model model, @PathVariable String username){
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        model.addAttribute("currentUsername", username);
        return "k-admin-user-list";
    }

    @GetMapping("/{currentUsername}/users/new")
    public String newUser(User user, @PathVariable("currentUsername") String username, Model model) {
        model.addAttribute("currentUsername", username);
        return "k-admin-new-user";
    }

    @PostMapping("/{currentUsername}/users/new")
    public String createUser(User user, @PathVariable("currentUsername") String username) {
        Set<Role> roles = user.getRoles();
        Role role = roleService.getByName(ERole.USER);
        roles.add(role);
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/" + username + "/users";
    }

    @GetMapping("/{currentUsername}/users/{userId}/edit")
    public String updateUserForm(@PathVariable("userId") Long id, Model model, @PathVariable("currentUsername") String username) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("currentUsername", username);
        return "k-admin-user-update";
    }

    @PostMapping("/{currentUsername}/users/{userId}/edit")
    public String updateUser(User user,
                             @PathVariable("currentUsername") String username,
                             @RequestParam(value = "isAdmin", required = false) boolean isAdmin) {

        Set<Role> roles = user.getRoles();
        Role role = roleService.getByName(ERole.USER);
        roles.add(role);

        if (isAdmin) {
            roles.add(new Role(ERole.ADMIN));
        } else {
            roles.remove(new Role(ERole.ADMIN));
        }
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/" + username + "/users";
    }

    @GetMapping("/{currentUsername}/users/{userId}/delete")
    public String deleteUser(@PathVariable("currentUsername") String username, @PathVariable("userId") Long id) {
        userService.deleteById(id);
        return "redirect:/" + username + "/users";
    }

}
