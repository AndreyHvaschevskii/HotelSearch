package com.hotelsearch.controller;

import com.hotelsearch.entity.User;
import com.hotelsearch.service.UserService;
import com.hotelsearch.validator.UserDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegController {

    private static final String USER_EXISTS = "User with the same name already exists." +
            "Please enter a different username";

    @Autowired
    UserService userService;

    @Autowired
    UserDataValidator userDataValidator;

    @GetMapping
    public String reg(String name, Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping
    public String reg(@Valid User user, BindingResult bindingResult,
                      Model model) {
        model.addAttribute(user);
        if (userDataValidator.hasRegError(bindingResult, user)) {
            model.addAttribute("messages", userDataValidator.listErrorForReg(bindingResult, user));
            return "sign-up";
        } else {
            if (userService.save(user)) {
                model.addAttribute("user", user);
                return "sign-in";
            } else {
                model.addAttribute("message", USER_EXISTS);
                return "sign-up";
            }
        }
    }
}