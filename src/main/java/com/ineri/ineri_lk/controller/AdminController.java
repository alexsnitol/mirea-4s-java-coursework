package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.User;
import com.ineri.ineri_lk.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Kozlov Alexander
 */

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}/users")
    public String findAll(Model model, @PathVariable String username){
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "k-user-list";
    }
}
