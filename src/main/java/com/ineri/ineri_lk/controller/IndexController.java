package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Kozlov Alexander
 */

@Controller
public class IndexController {

    @Autowired
    private AuthController authController;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/contacts")
    public ModelAndView showContact() {
        ModelAndView mv = new ModelAndView("view_contacts");
        mv = authController.setupUser(mv);
        return mv;
    }

    @GetMapping("/control-panel")
    public String showControl() {
        return "redirect:/users";
    }

    @GetMapping("/cookie")
    public ModelAndView showCookie() {
        ModelAndView mv = new ModelAndView("view_cookie");
        mv = authController.setupUser(mv);
        return mv;
    }

    @GetMapping("/view-template")
    public ModelAndView showViewTemplate() {
        ModelAndView mv = new ModelAndView("view_template");
        mv = authController.setupUser(mv);
        return mv;
    }

    @GetMapping("/edit-template")
    public ModelAndView showEditTemplate() {
        ModelAndView mv = new ModelAndView("edit_template");
        mv = authController.setupUser(mv);
        return mv;
    }

    @GetMapping("/add-template")
    public ModelAndView showAddTemplate() {
        ModelAndView mv = new ModelAndView("add_template");
        mv = authController.setupUser(mv);
        return mv;
    }

}
