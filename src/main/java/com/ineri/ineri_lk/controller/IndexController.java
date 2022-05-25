package com.ineri.ineri_lk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Kozlov Alexander
 */

@Controller
public class IndexController {

    @GetMapping("")
    public String index() {
        return "index";
    }
}
