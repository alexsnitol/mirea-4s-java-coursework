package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.City;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cities")
public class CityController {

    @GetMapping("/test")
    public String getCityById(Model model) {
        model.addAttribute("city", new City("TEST CITY"));
        return "cityTest";
    }

}
