package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.Favorite;
import com.ineri.ineri_lk.model.User;
import com.ineri.ineri_lk.repository.FavoriteRepository;
import com.ineri.ineri_lk.service.impl.FavoriteServiceImpl;
import com.ineri.ineri_lk.service.impl.RoleServiceImpl;
import com.ineri.ineri_lk.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kozlov Alexander
 */

@Controller
@RequestMapping("/{username}")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @Autowired
    private FavoriteServiceImpl favoriteServiceImpl;

    @Autowired
    private AuthController authController;

    @GetMapping("/edit")
    public ModelAndView updateUserForm(@PathVariable("username") String username) {
        ModelAndView mv = new ModelAndView("test_edit_user");
        mv = authController.setupUser(mv);
        return mv;
    }

    @PostMapping("/edit")
    public String updateUser(User user){
        user.getRoles().add(roleServiceImpl.getRoleById(2L));
        userServiceImpl.updateUser(user);
        return "redirect:/" + user.getUsername();
    }

    @GetMapping("/delete")
    public String deleteUser(@PathVariable("username") String username) {
        userServiceImpl.deleteByUsername(username);
        return "redirect:/";
    }

    @GetMapping("/favorites")
    public ModelAndView getUserFavorites(@PathVariable("username") String username) {
        ModelAndView mv = new ModelAndView("test_view_favorites");
        List<Favorite> favorites= favoriteServiceImpl.getUserFavorites(username);
        mv = authController.setupUser(mv);
        mv.addObject("favorites", favorites);
        return mv;
    }

    @GetMapping("/favorites/{advertisedId}/delete")
    public String deleteUserFavorite(
            @PathVariable("username") String username,
            @PathVariable("advertisedId") Long advertisedId)
    {
        favoriteServiceImpl.deleteByUsernameAndFavoriteId(username, advertisedId);
        return "redirect:/" + username + "/favorites";
    }

}
