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

    @GetMapping("/control")
    public String showControl() {
        return "view_controller";
    }

    @GetMapping("/cookie")
    public String showCookie() {
        return "cookie";
    }

    @GetMapping("/template")
    public String showTemplate() {
        return "view_template";
    }

}
