package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.PropertyType;
import com.ineri.ineri_lk.service.impl.PropertyTypeServiceImpl;
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
@RequestMapping("/property-types")
public class PropertyTypeController {

    @Autowired
    private PropertyTypeServiceImpl propertyTypeService;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView mv = new ModelAndView("view_property_types");

        mv.addObject("propertyTypes", propertyTypeService.getAll());

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView newPropertyType(PropertyType propertyType) {
        return new ModelAndView("test_new_property_type");
    }

    @PostMapping("/new")
    public String createPropertyType(PropertyType propertyType) {
        propertyTypeService.save(propertyType);
        return "redirect:/property-types";
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable("id") Long id) {
        propertyTypeService.deleteById(id);
        return "redirect:/property-types";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editById(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("test_edit_property_type");
        mv.addObject(propertyTypeService.getById(id));
        return mv;
    }

    @PostMapping("/{id}/edit")
    public String update(PropertyType propertyType) {
        propertyTypeService.save(propertyType);
        return "redirect:/property-types";
    }

}
