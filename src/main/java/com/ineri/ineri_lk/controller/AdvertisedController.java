package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.Advertised;
import com.ineri.ineri_lk.model.ERole;
import com.ineri.ineri_lk.model.Role;
import com.ineri.ineri_lk.model.User;
import com.ineri.ineri_lk.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Slotin Alexander (@alexsnitol)
 */

@Controller
@RequestMapping("/catalog")
public class AdvertisedController {

    @Autowired
    private AdvertisedServiceImpl advertisedService;
    @Autowired
    private UserService userService;
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
    @Autowired
    private AdvertisedStatusServiceImpl advertisedStatusService;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView mv = new ModelAndView("catalog");

        mv.addObject("advertiseds", advertisedService.getAll());


        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView getById(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("advertised");
        mv.addObject("advertised", advertisedService.getById(id));
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView newAdvertised(Advertised advertised) {
        ModelAndView mv = new ModelAndView("test_new_advertised");

        List<User> admins = userService.getAll().stream().filter(u -> u.getRoles().stream().anyMatch(r -> r.getName().equals(ERole.ROLE_ADMIN))).collect(Collectors.toList());
        List<User> users = userService.getAll().stream().filter(u -> u.getRoles().stream().noneMatch(r -> r.getName().equals(ERole.ROLE_ADMIN))).collect(Collectors.toList());

        mv.addObject("houseTypes", houseTypeService.getAll());
        mv.addObject("propertyTypes", propertyTypeService.getAll());
        mv.addObject("renovationTypes", renovationTypeService.getAll());
        mv.addObject("estateObjectTypes", estateObjectTypeService.getAll());
        mv.addObject("cities", cityService.getAll());
        mv.addObject("admins", admins);
        mv.addObject("users", users);
        mv.addObject("estateObjects", estateObjectService.getAll());
        mv.addObject("advertisedStatuses", advertisedStatusService.getAll());

        return mv;
    }

    @PostMapping("/new")
    public String createAdvertised(Advertised advertised) {
        advertised.setNowDateTime();
        advertisedService.save(advertised);
        return "redirect:/catalog/" + advertised.getId();
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        advertisedService.deleteById(id);
        return "redirect:/catalog";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editById(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("test_edit_advertised");

        List<User> admins = userService.getAll().stream().filter(u -> u.getRoles().stream().anyMatch(r -> r.getName().equals(ERole.ROLE_ADMIN))).collect(Collectors.toList());
        List<User> users = userService.getAll().stream().filter(u -> u.getRoles().stream().noneMatch(r -> r.getName().equals(ERole.ROLE_ADMIN))).collect(Collectors.toList());

        mv.addObject("houseTypes", houseTypeService.getAll());
        mv.addObject("propertyTypes", propertyTypeService.getAll());
        mv.addObject("renovationTypes", renovationTypeService.getAll());
        mv.addObject("estateObjectTypes", estateObjectTypeService.getAll());
        mv.addObject("cities", cityService.getAll());
        mv.addObject("admins", admins);
        mv.addObject("users", users);
        mv.addObject("estateObjects", estateObjectService.getAll());
        mv.addObject("advertisedStatuses", advertisedStatusService.getAll());
        mv.addObject("advertised", advertisedService.getById(id));

        return mv;
    }

    @PostMapping("/{id}/edit")
    public String update(Advertised advertised) {
        advertisedService.save(advertised);
        return "redirect:/catalog/" + advertised.getId();
    }

}
