package com.ineri.ineri_lk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Kozlov Alexander
 */

@Controller
public class LoginController {

    @GetMapping("login")
    public String loginPage() {
        return "";
    }
}
