package com.aibig.umk.controller.About;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aboutus")
public class aboutController {
    @GetMapping("/introduction")
    public String aboutus(Model model) {
        model.addAttribute("titlePage", "INTRODUCTION");
        model.addAttribute("breadcumbs1", "About Us");
        model.addAttribute("breadcumbs2", "Introduction");
        return "About/introduction";
    }

    @GetMapping("/director-desk")
    public String directorDesk(Model model) {
        model.addAttribute("titlePage", "DIRECTOR DESK");
        model.addAttribute("breadcumbs1", "About Us");
        model.addAttribute("breadcumbs2", "Director's Desk");
        return "About/director-desk";
    }

    @GetMapping("/vision-mission")
    public String visonMission(Model model) {
        model.addAttribute("titlePage", "VISION & MISSION");
        model.addAttribute("breadcumbs1", "About Us");
        model.addAttribute("breadcumbs2", "Vission & Mission");
        return "About/vision-mission";
    }

    @GetMapping("/achievement")
    public String achievement(Model model) {
        model.addAttribute("titlePage", "ACHIEVEMENT");
        model.addAttribute("breadcumbs1", "About Us");
        model.addAttribute("breadcumbs2", "Achievement");
        return "About/achievement";
    }
}
