package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.*;
import com.ineri.ineri_lk.service.impl.*;
import com.ineri.ineri_lk.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Slotin Alexander (@alexsnitol)
 */

@Controller
@RequestMapping("/catalog")
public class AdvertisedController {

    @Autowired
    private AuthController authController;

    @Autowired
    private AdvertisedServiceImpl advertisedService;
    @Autowired
    private UserServiceImpl userServiceImpl;
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
    private AdvertisedPhotoServiceImpl advertisedPhotoService;
    @Autowired
    private FavoriteServiceImpl favoriteService;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView mv = new ModelAndView("view_advertiseds");

        mv = authController.setupUser(mv);
        mv.addObject("lightTheme", true);
        mv.addObject("isAdmin", true);

        mv.addObject("advertiseds", advertisedService.getAll());

        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView getById(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("view_advertised");

        mv = authController.setupUser(mv);
        mv.addObject("lightTheme", true);

        mv.addObject("advertised", advertisedService.getById(id));

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView newAdvertised(Advertised advertised) {
        ModelAndView mv = new ModelAndView("new_advertised");

        List<User> admins = userServiceImpl.getAll().stream().filter(u -> u.getRoles().stream().anyMatch(r -> r.getAuthority().equals(Role.ROLE_ADMIN.getAuthority()))).collect(Collectors.toList());
        List<User> users = userServiceImpl.getAll().stream().filter(u -> u.getRoles().stream().noneMatch(r -> r.getAuthority().equals(Role.ROLE_ADMIN.getAuthority()))).collect(Collectors.toList());

        mv = authController.setupUser(mv);
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userServiceImpl.getUserByUsername(username);

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

        List<User> admins = userServiceImpl.getAll().stream().filter(u -> u.getRoles().stream().anyMatch(r -> r.getAuthority().equals(Role.ROLE_ADMIN.getAuthority()))).collect(Collectors.toList());
        List<User> users = userServiceImpl.getAll().stream().filter(u -> u.getRoles().stream().noneMatch(r -> r.getAuthority().equals(Role.ROLE_ADMIN.getAuthority()))).collect(Collectors.toList());

        mv = authController.setupUser(mv);
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

//        List<Long> photoIdList = new ArrayList<>();
//        advertisedService.getById(id).getAdvertisedPhoto().stream().forEach(e -> photoIdList.add(e.getId()));
//
//        mv.addObject("photoIdList", photoIdList);

        return mv;
    }

    @PostMapping("/{id}/edit")
    public String update(Advertised advertised, @RequestParam("images") List<MultipartFile> multipartFileList, @RequestParam(name = "photoIdList", defaultValue = "") ArrayList<Long> photoIdList) {

        Advertised advertisedBeforeChanges = advertisedService.getById(advertised.getId());
        List<AdvertisedPhoto> advertisedPhotoListCurrent = advertisedBeforeChanges.getAdvertisedPhoto();
        advertised.setAdvertisedPhoto(advertisedPhotoListCurrent);

        //Delete photos

        List<AdvertisedPhoto> advertisedPhotoListForDelete = advertisedPhotoListCurrent.stream().filter(ph -> !photoIdList.contains(ph.getId())).collect(Collectors.toList());
        for (AdvertisedPhoto advertisedPhoto : advertisedPhotoListForDelete) {
            FileUploadUtil.deleteFile("src/main/resources/" + advertisedPhoto.getPath());
            advertisedPhotoService.deleteById(advertisedPhoto.getId());
        }

        List<AdvertisedPhoto> advertisedPhotoListAfterDelete = advertisedPhotoListCurrent.stream().filter(ph -> photoIdList.contains(ph.getId())).collect(Collectors.toList());
        advertised.setAdvertisedPhoto(advertisedPhotoListAfterDelete);

        // Upload new photos

        if (!multipartFileList.get(0).getResource().getFilename().equals("")) {
            String fileName;
            String uploadDir = "src/main/resources/upload/catalog";
            int i = advertised.getAdvertisedPhoto().size();

            try {
                for (MultipartFile file : multipartFileList) {
                    fileName = advertised.getId() + "-" + i++ + ".jpg";
                    FileUploadUtil.saveFile(uploadDir, fileName, file);
                    advertisedPhotoService.saveAdvertisedPhoto(advertised, "/upload/catalog/" + fileName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        advertisedService.save(advertised);
        return "redirect:/catalog/" + advertised.getId();
    }

}
