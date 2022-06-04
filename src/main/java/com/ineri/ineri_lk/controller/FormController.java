package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.EFormState;
import com.ineri.ineri_lk.model.Form;
import com.ineri.ineri_lk.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Kozlov Alexander
 */

@Controller
@RequestMapping("/{username}/forms")
public class FormController {
    @Autowired
    private FormServiceImpl formServiceImpl;

    @Autowired
    private AuthController authController;

    @Autowired
    private HouseTypeServiceImpl houseTypeService;
    @Autowired
    private PropertyTypeServiceImpl propertyTypeService;
    @Autowired
    private RenovationTypeServiceImpl renovationTypeService;
    @Autowired
    private EstateObjectTypeServiceImpl estateObjectTypeService;
    @Autowired
    private AddressServiceImpl addressService;
    @Autowired
    private CityServiceImpl cityService;
    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ModelAndView getAll(@PathVariable("username") String username) {
        ModelAndView mv = new ModelAndView("test_view_forms");
        mv = authController.setupUser(mv);
        mv.addObject("forms", formServiceImpl.getAll());
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView newForm(@PathVariable("username") String username, Form form) {
        ModelAndView mv = new ModelAndView("test_new_form");
        mv = authController.setupUser(mv);

        mv.addObject("houseTypes", houseTypeService.getAll());
        mv.addObject("propertyTypes", propertyTypeService.getAll());
        mv.addObject("renovationTypes", renovationTypeService.getAll());
        mv.addObject("estateObjectTypes", estateObjectTypeService.getAll());
        mv.addObject("cities", cityService.getAll());
        mv.addObject("form", form);
        mv.addObject("address", addressService.getAll());

        return mv;
    }

    @PostMapping("/new")
    public String createEstateObject(@PathVariable("username") String username, Form form) {
        formServiceImpl.save(form);
        return "redirect:/" + username + "/forms";
    }

    @GetMapping("/{form_id}/edit")
    public ModelAndView edit(@PathVariable("username") String username, @PathVariable("form_id") Long id) {
        ModelAndView mv = new ModelAndView("test_edit_form");
        mv = authController.setupUser(mv);

        mv.addObject("houseTypes", houseTypeService.getAll());
        mv.addObject("propertyTypes", propertyTypeService.getAll());
        mv.addObject("renovationTypes", renovationTypeService.getAll());
        mv.addObject("estateObjectTypes", estateObjectTypeService.getAll());
        mv.addObject("cities", cityService.getAll());
        mv.addObject("form", formServiceImpl.getById(id));
        mv.addObject("address", addressService.getAll());
        return mv;
    }

    @PostMapping("/{form_id}/edit")
    public String update(@PathVariable("username") String username, Form form, @PathVariable("form_id") Long id) {
        formServiceImpl.save(form);
        return "redirect:/" + username + "/forms";
    }

    @GetMapping("/{form_id}")
    public ModelAndView showForm(@PathVariable("username") String username, Form form, @PathVariable("form_id") Long id) {
        ModelAndView mv = new ModelAndView("test_view_form");

        mv = authController.setupUser(mv);
        mv.addObject("form", form);

        return mv;
    }

    @GetMapping("/{form_id}/publish")
    public String publishForm(@PathVariable("username") String username, @PathVariable("form_id") Long id) {
//        Form form = formServiceImpl.getById(id);
//        form.setState(EFormState.ACCEPTED);
//        formServiceImpl.save(form);
        return "redirect:/" + username + "/forms";
    }

    @GetMapping("/{form_id}/reject")
    public String rejectForm(@PathVariable("username") String username, @PathVariable("form_id") Long id) {
//        Form form = formServiceImpl.getById(id);
//        form.setState(EFormState.DENIED);
//        formServiceImpl.save(form);
        return "redirect:/" + username + "/forms";
    }

    @GetMapping("/{form_id}/delete")
    public String delete(@PathVariable("username") String username, @PathVariable("form_id") Long id) {
        formServiceImpl.deleteById(id);
        return "redirect:/" + username + "/forms";
    }
}
