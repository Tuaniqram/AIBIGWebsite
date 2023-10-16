package com.aibig.umk.controller.About;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aboutus")
public class aboutController {
    @GetMapping("/introduction")
    public String aboutus() {
        return "About/introduction";
    }

    @GetMapping("/director-desk")
    public String directorDesk() {
        return "About/director-desk";
    }

    @GetMapping("/vision-mission")
    public String visonMission() {
        return "About/vision-mission";
    }

    @GetMapping("/achievement")
    public String achievement() {
        return "About/achievement";
    }
}
