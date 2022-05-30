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

import javax.crypto.BadPaddingException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Kozlov Alexander
 */

@Controller
@RequestMapping("/users")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String findAll(Model model){
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "test_view_users";
    }

    @GetMapping("/new")
    public String newUserForm() {
        return "test_new_user";
    }

    @PostMapping("/new")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{userId}/edit")
    public String updateUserForm(@PathVariable("userId") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("isAdmin", user.getRoles().contains(new Role(ERole.ROLE_ADMIN)));
        return "test_edit_user";
    }

    @PostMapping("/{userId}/edit")
    public String updateUser(@PathVariable("userId") Long id,
                             User user,
                             @RequestParam(value = "isAdmin", required = false, defaultValue = "off")
                             String isAdmin) {
        user.getRoles().add(roleService.getRoleById(2L));
        if (isAdmin.equals("on"))
            user.getRoles().add(roleService.getRoleById(1L));
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{userId}/delete")
    public String deleteUser(@PathVariable("userId") Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

}
