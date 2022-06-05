package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.User;
import com.ineri.ineri_lk.service.impl.UserServiceImpl;
import com.ineri.ineri_lk.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Kozlov Alexander
 */

@Controller
public class SignupController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/signup")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String saveUser(@ModelAttribute("user") User user, Model model, @RequestParam("image") MultipartFile multipartFile) {
        User userDB = userServiceImpl.getUserByUsername(user.getUsername());

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("passwordError", "Пароли не совпадают!");
            return "signup";
        } else {
            uploadPhoto(user, multipartFile);
            userServiceImpl.saveUser(user);
            return "redirect:/login";
        }
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
}
