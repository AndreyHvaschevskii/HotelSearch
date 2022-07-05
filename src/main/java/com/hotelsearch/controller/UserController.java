package com.hotelsearch.controller;

import com.hotelsearch.entity.User;
import com.hotelsearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user-info")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping
    public String getUser(Model model) {
        User userFromModel = ((User) httpSession.getAttribute("user"));
        User user = userService.findUserByLogin(userFromModel);
        model.addAttribute("user", user);
        return "user-info";
    }

}
