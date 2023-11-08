package com.aibig.umk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class main {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/contact-us")
    public String contactUs(Model model) {
        model.addAttribute("breadcumbs1", "Home");
        model.addAttribute("breadcumbs2", "Contact Us");

        return "contact-us";
    }
}
