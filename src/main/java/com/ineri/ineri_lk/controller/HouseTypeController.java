package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.HouseType;
import com.ineri.ineri_lk.service.impl.HouseTypeServiceImpl;
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
@RequestMapping("/house-types")
public class HouseTypeController {

    @Autowired
    private HouseTypeServiceImpl houseTypeService;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView mv = new ModelAndView("view_house_types");

        mv.addObject("houseTypes", houseTypeService.getAll());

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView newHouseType(HouseType houseType) {
        return new ModelAndView("new_house_type");
    }

    @PostMapping("/new")
    public String createHouseType(HouseType houseType) {
        houseTypeService.save(houseType);
        return "redirect:/house-types";
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable("id") Long id) {
        houseTypeService.deleteById(id);
        return "redirect:/house-types";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editById(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("edit_house_type");
        mv.addObject(houseTypeService.getById(id));
        return mv;
    }

    @PostMapping("/{id}/edit")
    public String update(HouseType houseType) {
        houseTypeService.save(houseType);
        return "redirect:/house-types";
    }

}
