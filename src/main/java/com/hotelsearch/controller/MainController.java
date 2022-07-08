package com.hotelsearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String home() {

        return "index";
    }

    @PostMapping
    public String saveSearch(@ModelAttribute String country, Date date1, Date date2, int person, Model model) {
        model.addAttribute("country", country);
        model.addAttribute("date1", date1);
        model.addAttribute("date2", date2);
        model.addAttribute("person", person);
        return "index";
    }
}
