package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.User;
import com.ineri.ineri_lk.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Kozlov Alexander
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public String account() {
        return "account";
    }

    @GetMapping("/{username}/edit")
    public String updateUserForm(@PathVariable("username") String username, Model model) {
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "k-user-update";
    }

    @PostMapping("/{username}/edit")
    public String updateUser(User user){
        userService.updateUser(user);
        return "redirect:/" + user.getUsername();
    }

    @GetMapping("/{username}/delete")
    public String deleteUser(@PathVariable("username") String username) {
        userService.deleteByUsername(username);
        return "redirect:/";
    }

}
