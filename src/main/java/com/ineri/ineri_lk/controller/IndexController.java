package com.ineri.ineri_lk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Kozlov Alexander
 */

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/contacts")
    public String showContact() {
        return "view_contacts";
    }

    @GetMapping("/control-panel")
    public String showControl() {
        return "redirect:/users";
    }

    @GetMapping("/cookie")
    public String showCookie() {
        return "cookie";
    }

    @GetMapping("/view-template")
    public String showViewTemplate() {
        return "view_template";
    }

    @GetMapping("/edit-template")
    public String showEditTemplate() {
        return "edit_template";
    }

    @GetMapping("/add-template")
    public String showAddTemplate() {
        return "add_template";
    }

}
