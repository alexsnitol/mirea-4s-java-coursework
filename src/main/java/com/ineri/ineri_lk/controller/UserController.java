package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.Favorite;
import com.ineri.ineri_lk.model.Role;
import com.ineri.ineri_lk.model.User;
import com.ineri.ineri_lk.service.impl.FavoriteServiceImpl;
import com.ineri.ineri_lk.service.impl.UserServiceImpl;
import com.ineri.ineri_lk.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

/**
 * @author Kozlov Alexander
 */

@Controller
@RequestMapping("/{username}")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private FavoriteServiceImpl favoriteServiceImpl;

    @Autowired
    private AuthController authController;

    @GetMapping("/profile")
    public ModelAndView showProfile(@PathVariable("username") String username) {
        ModelAndView mv = new ModelAndView("view_profile");
        mv = authController.setupUser(mv);
        return mv;
    }

    @GetMapping("/profile/edit")
    public ModelAndView updateUserForm(@PathVariable("username") String username) {
        ModelAndView mv = new ModelAndView("edit_profile");
        authController.setupUser(mv);
        return mv;
    }

    @PostMapping("/profile/edit")
    public String updateUser(User user, @RequestParam("image") MultipartFile multipartFile){
        uploadPhoto(user, multipartFile);
        user.getRoles().add(Role.ROLE_USER);
        userServiceImpl.updateUser(user);
        return "redirect:/" + user.getUsername() + "/profile";
    }

    private void uploadPhoto(User user, MultipartFile multipartFile) {
        if (!multipartFile.getResource().getFilename().equals("")) {
            String fileName = user.getId() + ".jpg";
            String uploadDir = "src/main/resources/upload/userdata";
            try {
                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
                user.setPhotoPath("/upload/userdata/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/favorites")
    public ModelAndView getUserFavorites(@PathVariable("username") String username) {
        ModelAndView mv = new ModelAndView("view_favorites");
        List<Favorite> favorites= favoriteServiceImpl.getUserFavorites(username);

        mv = authController.setupUser(mv);

        mv.addObject("lightTheme", true);

        mv.addObject("favorites", favorites);

        return mv;
    }

    @GetMapping("/favorites/{advertisedId}/delete")
    public String deleteUserFavorite(
            @PathVariable("username") String username,
            @PathVariable("advertisedId") Long advertisedId)
    {
        User user = userServiceImpl.getUserByUsername(username);
        favoriteServiceImpl.deleteByUserIdAndFavoriteId(user.getId(), advertisedId);
        return "redirect:/" + username + "/favorites";
    }

}
