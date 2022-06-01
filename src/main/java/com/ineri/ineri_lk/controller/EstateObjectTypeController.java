package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.EstateObjectType;
import com.ineri.ineri_lk.service.impl.EstateObjectTypeServiceImpl;
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
@RequestMapping("/estate-object-types")
public class EstateObjectTypeController {

    @Autowired
    private EstateObjectTypeServiceImpl estateObjectTypeService;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView mv = new ModelAndView("view_estate_object_types");

        mv.addObject("estateObjectTypes", estateObjectTypeService.getAll());

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView newEstateObjectType(EstateObjectType estateObjectType) {
        return new ModelAndView("new_estate_object_type");
    }

    @PostMapping("/new")
    public String createEstateObjectType(EstateObjectType estateObjectType) {
        estateObjectTypeService.save(estateObjectType);
        return "redirect:/estate-object-types";
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable("id") Long id) {
        estateObjectTypeService.deleteById(id);
        return "redirect:/estate-object-types";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editById(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("edit_estate_object_type");
        mv.addObject(estateObjectTypeService.getById(id));
        return mv;
    }

    @PostMapping("/{id}/edit")
    public String update(EstateObjectType estateObjectType) {
        estateObjectTypeService.save(estateObjectType);
        return "redirect:/estate-object-types";
    }

}
