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
import java.util.Objects;

/**
 * @author Slotin Alexander (@alexsnitol)
 */

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
        ModelAndView mv = new ModelAndView("view_vacancies");

        mv.addObject("vacancies", vacancyService.getAll());

        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView getById(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("view_vacancy");

        Vacancy vacancy = vacancyService.getById(id);

        mv.addObject("vacancy", vacancy);

        String textDescription = vacancy.getTextDescription();
        String textFeatures = vacancy.getTextFeatures();
        String textRequirements = vacancy.getTextRequirements();
        String textResponsibilities = vacancy.getTextResponsibilities();
        String textStudy = vacancy.getTextStudy();
        String textSummary = vacancy.getTextSummary();

        if (Objects.equals(textDescription, "")) {
            textDescription = null;
        }
        if (Objects.equals(textFeatures, "")) {
            textFeatures = null;
        }
        if (Objects.equals(textRequirements, "")) {
            textRequirements = null;
        }
        if (Objects.equals(textResponsibilities, "")) {
            textResponsibilities = null;
        }
        if (Objects.equals(textStudy, "")) {
            textStudy = null;
        }
        if (Objects.equals(textSummary, "")) {
            textSummary = null;
        }

        mv.addObject("textDescription",      NewLineConverter.convert(textDescription));
        mv.addObject("textFeatures",         NewLineConverter.convert(textFeatures));
        mv.addObject("textRequirements",     NewLineConverter.convert(textRequirements));
        mv.addObject("textResponsibilities", NewLineConverter.convert(textResponsibilities));
        mv.addObject("textStudy",            NewLineConverter.convert(textStudy));
        mv.addObject("textSummary",          NewLineConverter.convert(textSummary));

        return mv;
    }

    @GetMapping("/{id}/set-active")
    public String setActiveStatus(@PathVariable("id") Long id, @RequestParam("is-active") boolean activeStatus) {
        vacancyService.setActiveStatus(id, activeStatus);
        return "redirect:/vacancies/" + id;
    }

    @GetMapping("/new")
    public ModelAndView newVacancy(Vacancy vacancy) {
        ModelAndView mv = new ModelAndView("new_vacancy");

        List<City> cities = cityService.getAll();
        List<User> users = userService.getAll();
        vacancy.setActive(true);

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

    @GetMapping("/{id}/edit")
    public ModelAndView editVacancy(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("edit_vacancy");

        List<City> cities = cityService.getAll();
        List<User> users = userService.getAll();

        mv.addObject(vacancyService.getById(id));
        mv.addObject("cities", cities);
        mv.addObject("users", users);

        return mv;
    }

    @PostMapping("/{id}/edit")
    public String update(Vacancy vacancy) {
        vacancyService.save(vacancy);
        return "redirect:/vacancies/" + vacancy.getId();
    }

}
