package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.City;
import com.ineri.ineri_lk.service.impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Slotin Alexander (@alexsnitol)
 */

@Controller
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityServiceImpl cityService;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView mv = new ModelAndView("view_cities");

        mv.addObject("cities", cityService.getAll());
        Boolean admin = false;
        mv.addObject("isAdmin", admin);
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView newCity(City city) {
        return new ModelAndView("new_city");
    }

    @PostMapping("/new")
    public String createCity(City city) {
        cityService.save(city);
        return "redirect:/cities";
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable("id") Long id) {
        cityService.deleteById(id);
        return "redirect:/cities";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editById(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("edit_city");
        mv.addObject(cityService.getById(id));
        return mv;
    }

    @PostMapping("/{id}/edit")
    public String update(City city) {
        cityService.save(city);
        return "redirect:/cities";
    }

}
