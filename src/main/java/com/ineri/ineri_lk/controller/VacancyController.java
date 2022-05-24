package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.Vacancy;
import com.ineri.ineri_lk.service.impl.VacancyServiceImpl;
import com.ineri.ineri_lk.util.NewLineConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/vacancies")
public class VacancyController extends AbstractController {

    @Autowired
    VacancyServiceImpl vacancyService;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView mv = new ModelAndView("vacancies");

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
    public String newVacancy() {
        return "test_new_vacancy";
    }

    @PostMapping("/new")
    public String createVacancy(Vacancy vacancy) {
        vacancyService.save(vacancy);
        return "redirect:/vacancies";
    }

}
