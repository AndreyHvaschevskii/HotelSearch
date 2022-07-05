package com.hotelsearch.controller;

import com.hotelsearch.entity.User;
import com.hotelsearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/")
public class MainController {

    private static final String USER_DOES_NOT_EXIST = "User with this name does not exist.";

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return "index";
    }

    @PostMapping
    public String searchByName(String name, Model model) {
        if (userService.userExistsByName(name)) {
            User user = userService.findUserByName(name);
            model.addAttribute("user", user);
            return "user-info";
        } else {
            model.addAttribute("message", USER_DOES_NOT_EXIST);
            return "index";
        }
    }
}
