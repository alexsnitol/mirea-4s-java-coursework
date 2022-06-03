package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.User;
import com.ineri.ineri_lk.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Kozlov Alexander
 */

@Controller
public class AuthController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    public ModelAndView setupUser(ModelAndView mv) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userServiceImpl.getUserByUsername(username);

        if (user == null) {
            return mv;
        }

        String fio = user.getSurname()+ " " + user.getName().charAt(0) + ". " + user.getPatronymic().charAt(0) + ".";
        mv.addObject("username", username);
        mv.addObject("isAdmin", userServiceImpl.checkAdminRole(username));
        mv.addObject("user", user);
        mv.addObject("FIO", fio);
        return mv;
    }
}
