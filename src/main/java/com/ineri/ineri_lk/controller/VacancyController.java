package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.City;
import com.ineri.ineri_lk.model.User;
import com.ineri.ineri_lk.model.Vacancy;
import com.ineri.ineri_lk.service.impl.CityServiceImpl;
import com.ineri.ineri_lk.service.impl.UserService;
import com.ineri.ineri_lk.service.impl.VacancyServiceImpl;
import com.ineri.ineri_lk.util.NewLineConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/vacancies")
public class VacancyController extends AbstractController {

    @Autowired
    VacancyServiceImpl vacancyService;
    @Autowired
    CityServiceImpl cityService;

    @Autowired
    UserService userService;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView mv = new ModelAndView("test_vacancies");

        mv.addObject("vacancies", vacancyService.getAll());

        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView getById(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("test_vacancy");

        Vacancy vacancy = vacancyService.getById(id);

        mv.addObject("vacancy", vacancy);
        mv.addObject("textDescription",      NewLineConverter.convert(vacancy.getTextDescription()));
        mv.addObject("textFeatures",         NewLineConverter.convert(vacancy.getTextFeatures()));
        mv.addObject("textRequirements",     NewLineConverter.convert(vacancy.getTextRequirements()));
        mv.addObject("textResponsibilities", NewLineConverter.convert(vacancy.getTextResponsibilities()));
        mv.addObject("textStudy",            NewLineConverter.convert(vacancy.getTextStudy()));
        mv.addObject("textSummary",          NewLineConverter.convert(vacancy.getTextSummary()));

        return mv;
    }

    @GetMapping("/{id}/set-active")
    public String setActiveStatus(@PathVariable("id") Long id, @RequestParam("is-active") boolean activeStatus) {
        vacancyService.setActiveStatus(id, activeStatus);
        return "redirect:/vacancies/" + id;
    }

    @GetMapping("/new")
    public ModelAndView newVacancy(Vacancy vacancy) {
        ModelAndView mv = new ModelAndView("test_new_vacancy");

        List<City> cities = cityService.getAll();
        List<User> users = userService.getAll();

        mv.addObject("cities", cities);
        mv.addObject("users", users);

        return mv;
    }

    @PostMapping("/new")
    public String createVacancy(Vacancy vacancy) {
        vacancyService.save(vacancy);
        return "redirect:/vacancies";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        vacancyService.deleteById(id);
        return "redirect:/vacancies";
    }

}
