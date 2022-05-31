package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.RenovationType;
import com.ineri.ineri_lk.service.impl.RenovationTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/renovation-types")
public class RenovationTypeController {

    @Autowired
    private RenovationTypeServiceImpl renovationTypeService;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView mv = new ModelAndView("view_renovation_types");

        mv.addObject("renovationTypes", renovationTypeService.getAll());

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView newRenovationType(RenovationType renovationType) {
        return new ModelAndView("test_new_renovation_type");
    }

    @PostMapping("/new")
    public String createRenovationType(RenovationType renovationType) {
        renovationTypeService.save(renovationType);
        return "redirect:/renovation-types";
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable("id") Long id) {
        renovationTypeService.deleteById(id);
        return "redirect:/renovation-types";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editById(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("test_edit_renovation_type");
        mv.addObject(renovationTypeService.getById(id));
        return mv;
    }

    @PostMapping("/{id}/edit")
    public String update(RenovationType renovationType) {
        renovationTypeService.save(renovationType);
        return "redirect:/renovation-types";
    }

}
