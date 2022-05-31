package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.EstateObject;
import com.ineri.ineri_lk.service.impl.*;
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

@RequestMapping("/estate-objects")
@Controller
public class EstateObjectController extends AbstractController {

    @Autowired
    EstateObjectServiceImpl estateObjectService;
    @Autowired
    HouseTypeServiceImpl houseTypeService;
    @Autowired
    PropertyTypeServiceImpl propertyTypeService;
    @Autowired
    RenovationTypeServiceImpl renovationTypeService;
    @Autowired
    EstateObjectTypeServiceImpl estateObjectTypeService;
    @Autowired
    CityServiceImpl cityService;
    @Autowired
    AddressServiceImpl addressService;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView mv = new ModelAndView("view_estate_objects");
        mv.addObject("estateObjects", estateObjectService.getAll());
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView newEstateObject(EstateObject estateObject) {
        ModelAndView mv = new ModelAndView("test_new_estate_object");

        mv.addObject("houseTypes", houseTypeService.getAll());
        mv.addObject("propertyTypes", propertyTypeService.getAll());
        mv.addObject("renovationTypes", renovationTypeService.getAll());
        mv.addObject("estateObjectTypes", estateObjectTypeService.getAll());
        mv.addObject("cities", cityService.getAll());

        return mv;
    }

    @PostMapping("/new")
    public String createEstateObject(EstateObject estateObject) {
        estateObject.setNowDateTime();
        estateObjectService.save(estateObject);

        return "redirect:/estate-objects";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("test_edit_estate_object");

        mv.addObject("estateObject", estateObjectService.getById(id));
        mv.addObject("houseTypes", houseTypeService.getAll());
        mv.addObject("propertyTypes", propertyTypeService.getAll());
        mv.addObject("renovationTypes", renovationTypeService.getAll());
        mv.addObject("estateObjectTypes", estateObjectTypeService.getAll());
        mv.addObject("cities", cityService.getAll());

        return mv;
    }

    @PostMapping("/{id}/edit")
    public String update(EstateObject estateObject, @PathVariable Long id) {
        estateObjectService.save(estateObject);
        return "redirect:/estate-objects";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        estateObjectService.deleteById(id);
        return "redirect:/estate-objects";
    }

}
