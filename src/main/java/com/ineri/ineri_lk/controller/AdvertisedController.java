package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.*;
import com.ineri.ineri_lk.service.impl.*;
import com.ineri.ineri_lk.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    private UserServiceImpl userService;
    @Autowired
    private EstateObjectServiceImpl estateObjectService;
    @Autowired
    private HouseTypeServiceImpl houseTypeService;
    @Autowired
    private PropertyTypeServiceImpl propertyTypeService;
    @Autowired
    private RenovationTypeServiceImpl renovationTypeService;
    @Autowired
    private EstateObjectTypeServiceImpl estateObjectTypeService;
    @Autowired
    private CityServiceImpl cityService;
    @Autowired
    private AddressServiceImpl addressService;
    @Autowired
    private AdvertisedStatusServiceImpl advertisedStatusService;
    @Autowired
    private FavoriteServiceImpl favoriteService;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView mv = new ModelAndView("view_advertiseds");

        mv.addObject("lightTheme", true);
        mv.addObject("isAdmin", true);

        mv.addObject("advertiseds", advertisedService.getAll());

        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView getById(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("view_advertised");

        mv.addObject("lightTheme", true);

        mv.addObject("advertised", advertisedService.getById(id));

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView newAdvertised(Advertised advertised) {
        ModelAndView mv = new ModelAndView("new_advertised");

        List<User> admins = userService.getAll().stream().filter(u -> u.getRoles().stream().anyMatch(r -> r.getAuthority().equals(Role.ROLE_ADMIN.getAuthority()))).collect(Collectors.toList());
        List<User> users = userService.getAll().stream().filter(u -> u.getRoles().stream().noneMatch(r -> r.getAuthority().equals(Role.ROLE_ADMIN.getAuthority()))).collect(Collectors.toList());

        mv.addObject("lightTheme", true);

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

    @PostMapping("/{id}/add-to-favorites")
    public String addToFavorites(@PathVariable Long id, HttpServletRequest request) {

        //TODO: загрузить сюда юзера
        User user = null;
        Advertised advertised = advertisedService.getById(id);

        favoriteService.save(new Favorite(user, advertised));

        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        advertisedService.deleteById(id);
        return "redirect:/catalog";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editById(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("edit_advertised");

        List<User> admins = userService.getAll().stream().filter(u -> u.getRoles().stream().anyMatch(r -> r.getAuthority().equals(Role.ROLE_ADMIN.getAuthority()))).collect(Collectors.toList());
        List<User> users = userService.getAll().stream().filter(u -> u.getRoles().stream().noneMatch(r -> r.getAuthority().equals(Role.ROLE_ADMIN.getAuthority()))).collect(Collectors.toList());

        mv.addObject("lightTheme", true);
        mv.addObject("isAdmin", true);

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
    public String update(Advertised advertised, @RequestParam("image") MultipartFile multipartFile) {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "/images/userdata/" + advertised.getId() + "-0.jpg";

        try {
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        advertisedService.save(advertised);
        return "redirect:/catalog/" + advertised.getId();
    }

}
