package com.aibig.umk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class main {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    // @GetMapping("/test")
    // public String test() {
    // return "Navtest";
    // }
}
