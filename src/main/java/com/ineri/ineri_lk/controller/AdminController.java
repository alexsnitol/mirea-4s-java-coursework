package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.ERole;
import com.ineri.ineri_lk.model.Role;
import com.ineri.ineri_lk.model.User;
import com.ineri.ineri_lk.service.impl.RoleServiceImpl;
import com.ineri.ineri_lk.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Kozlov Alexander
 */

@Controller
@RequestMapping("/users")
public class AdminController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @Autowired
    private AuthController authController;

    @GetMapping
    public String findAll(Model model){
        List<User> users = userServiceImpl.getAll();
        model.addAttribute("users", users);
        return "view_users";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "new_user";
    }

    @PostMapping("/new")
    public String createUser(User user) {
        userServiceImpl.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{userId}/edit")
    public String updateUserForm(@PathVariable("userId") Long id, Model model) {
        User user = userServiceImpl.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("isAdmin", user.getRoles().contains(new Role(ERole.ROLE_ADMIN)));
        return "edit_user";
    }

    @PostMapping("/{userId}/edit")
    public String updateUser(@PathVariable("userId") Long id,
                             User user,
                             @RequestParam(value = "isAdmin", required = false, defaultValue = "off")
                             String isAdmin) {
        user.getRoles().add(roleServiceImpl.getRoleById(2L));
        if (isAdmin.equals("on"))
            user.getRoles().add(roleServiceImpl.getRoleById(1L));
        userServiceImpl.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{userId}/delete")
    public String deleteUser(@PathVariable("userId") Long id) {
        userServiceImpl.deleteById(id);
        return "redirect:/users";
    }

}
