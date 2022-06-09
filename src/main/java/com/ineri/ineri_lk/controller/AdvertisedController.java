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
    public ModelAndView getAll(@RequestParam(defaultValue = "") String  city,
                               @RequestParam(defaultValue = "") Float   areaMin,
                               @RequestParam(defaultValue = "") Float   areaMax,
                               @RequestParam(defaultValue = "") Integer floorMin,
                               @RequestParam(defaultValue = "") Integer floorMax,
                               @RequestParam(defaultValue = "") Integer houseFloorMin,
                               @RequestParam(defaultValue = "") Integer houseFloorMax,
                               @RequestParam(defaultValue = "") Integer roomSizeMin,
                               @RequestParam(defaultValue = "") Integer roomSizeMax,
                               @RequestParam(defaultValue = "") String  houseType,
                               @RequestParam(defaultValue = "") String  propertyType,
                               @RequestParam(defaultValue = "") String  renovationType,
                               @RequestParam(defaultValue = "") String  estateObjectType) {

        ModelAndView mv = new ModelAndView("view_advertiseds");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userServiceImpl.getUserByUsername(username);

        mv = authController.setupUser(mv);
        mv.addObject("lightTheme", true);
        
        city                 = advertisedService.checkFilterParam(city);
        areaMin              = advertisedService.checkFilterParam(areaMin);
        areaMax              = advertisedService.checkFilterParam(areaMax);
        floorMin             = advertisedService.checkFilterParam(floorMin);
        floorMax             = advertisedService.checkFilterParam(floorMax);
        houseFloorMin        = advertisedService.checkFilterParam(houseFloorMin);
        houseFloorMax        = advertisedService.checkFilterParam(houseFloorMax);
        roomSizeMin          = advertisedService.checkFilterParam(roomSizeMin);
        roomSizeMax          = advertisedService.checkFilterParam(roomSizeMax);
        houseType            = advertisedService.checkFilterParam(houseType);
        propertyType         = advertisedService.checkFilterParam(propertyType);
        renovationType       = advertisedService.checkFilterParam(renovationType);
        estateObjectType     = advertisedService.checkFilterParam(estateObjectType);

        mv.addObject("selectedCity",             city != null ? city : "");
        mv.addObject("selectedAreaMin",          areaMin != null ? areaMin : "");
        mv.addObject("selectedAreaMax",          areaMax != null ? areaMax : "");
        mv.addObject("selectedFloorMin",         floorMin != null ? floorMin : "");
        mv.addObject("selectedFloorMax",         floorMax != null ? floorMax : "");
        mv.addObject("selectedHouseFloorMin",         houseFloorMin != null ? houseFloorMin : "");
        mv.addObject("selectedHouseFloorMax",         houseFloorMax != null ? houseFloorMax : "");
        mv.addObject("selectedRoomSizeMin",      roomSizeMin != null ? roomSizeMin : "");
        mv.addObject("selectedRoomSizeMax",      roomSizeMax != null ? roomSizeMax : "");
        mv.addObject("selectedHouseType",        houseType != null ? houseType : "");
        mv.addObject("selectedPropertyType",     propertyType != null ? propertyType : "");
        mv.addObject("selectedRenovationType",   renovationType != null ? renovationType : "");
        mv.addObject("selectedEstateObjectType", estateObjectType != null ? estateObjectType : "");
        
        List<Advertised> advertisedList = advertisedService.getAllFiltered(city, areaMin, areaMax, floorMin, floorMax, houseFloorMin, houseFloorMax, roomSizeMin, roomSizeMax, houseType, propertyType, renovationType, estateObjectType);

        mv.addObject("advertiseds", advertisedList);
        if (user != null) {
            mv.addObject("favorites", user.getFavorites());
        }

        mv.addObject("houseTypes", houseTypeService.getAll());
        mv.addObject("propertyTypes", propertyTypeService.getAll());
        mv.addObject("renovationTypes", renovationTypeService.getAll());
        mv.addObject("estateObjectTypes", estateObjectTypeService.getAll());
        mv.addObject("cities", cityService.getAll());
        mv.addObject("estateObjects", estateObjectService.getAll());
        mv.addObject("advertisedStatuses", advertisedStatusService.getAll());

        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView getById(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("view_advertised");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userServiceImpl.getUserByUsername(username);

        mv = authController.setupUser(mv);
        mv.addObject("lightTheme", true);

        boolean isFavorite = false;
        if (user != null) {
            isFavorite = favoriteService.existByUserIdAndAdvertisedId(user, advertisedService.getById(id));
        }

        mv.addObject("advertised", advertisedService.getById(id));
        mv.addObject("isFavorite", isFavorite);

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView newAdvertised(Advertised advertised) {
        ModelAndView mv = new ModelAndView("new_advertised");

        List<User> admins = userServiceImpl.getAll().stream().filter(u -> u.getRoles().stream().anyMatch(r -> r.getAuthority().equals(Role.ROLE_ADMIN.getAuthority()))).collect(Collectors.toList());
        List<User> users = userServiceImpl.getAll().stream().filter(u -> u.getRoles().stream().noneMatch(r -> r.getAuthority().equals(Role.ROLE_ADMIN.getAuthority()))).collect(Collectors.toList());

        mv = authController.setupUser(mv);

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
    public String createAdvertised(Advertised advertised, @RequestParam("images") List<MultipartFile> multipartFileList) {
        advertised.setNowDateTime();
        advertisedService.save(advertised);
        uploadPhotos(advertised, multipartFileList);
        return "redirect:/catalog/" + advertised.getId();
    }

    @GetMapping("/{id}/add-to-favorites")
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
    public String update(Advertised advertised, @RequestParam("images") List<MultipartFile> multipartFileList, @RequestParam(name = "photoIdList", defaultValue = "") ArrayList<Long> photoIdList) {

        Advertised advertisedBeforeChanges = advertisedService.getById(advertised.getId());
        List<AdvertisedPhoto> advertisedPhotoListCurrent = advertisedBeforeChanges.getAdvertisedPhoto();
        advertised.setAdvertisedPhoto(advertisedPhotoListCurrent);
        advertised.setDateTimeCreated(advertisedBeforeChanges.getDateTimeCreated());

        //Delete photos

        List<AdvertisedPhoto> advertisedPhotoListForDelete = advertisedPhotoListCurrent.stream().filter(ph -> !photoIdList.contains(ph.getId())).collect(Collectors.toList());
        for (AdvertisedPhoto advertisedPhoto : advertisedPhotoListForDelete) {
            FileUploadUtil.deleteFile("src/main/resources/" + advertisedPhoto.getPath());
            advertisedPhotoService.deleteById(advertisedPhoto.getId());
        }

        List<AdvertisedPhoto> advertisedPhotoListAfterDelete = advertisedPhotoListCurrent.stream().filter(ph -> photoIdList.contains(ph.getId())).collect(Collectors.toList());
        advertised.setAdvertisedPhoto(advertisedPhotoListAfterDelete);

        uploadPhotos(advertised, multipartFileList);

        advertisedService.save(advertised);
        return "redirect:/catalog/" + advertised.getId();
    }

    private void uploadPhotos(Advertised advertised, List<MultipartFile> multipartFileList) {
        if (!multipartFileList.get(0).getResource().getFilename().equals("")) {
            String fileName;
            String uploadDir = "src/main/resources/upload/catalog";

            int i = 0;
            if (advertised.getAdvertisedPhoto() != null) {
                i = advertised.getAdvertisedPhoto().size();
            }

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
    }

}
