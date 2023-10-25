package com.aibig.umk.controller.Group;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/group")
public class groupController {
    @GetMapping("/team")
    public String team(Model model) {
        model.addAttribute("breadcumbs1", "Group");
        model.addAttribute("breadcumbs2", "Team");
        return "Group/Team";
    }

    @GetMapping("/researchmember")
    public String researchmember(Model model) {
        model.addAttribute("breadcumbs1", "Group");
        model.addAttribute("breadcumbs2", "Research Member");
        return "Group/ResearchMember";
    }

    @GetMapping("/scientific-advisory")
    public String scientificAdvisory(Model model) {
        model.addAttribute("breadcumbs1", "Group");
        model.addAttribute("breadcumbs2", "Scientific Advisory");
        return "Group/ScientificAdvisory";
    }

    @GetMapping("/reference-committee")
    public String referenceCommittee(Model model) {
        model.addAttribute("breadcumbs1", "Group");
        model.addAttribute("breadcumbs2", "Reference Committee");
        return "Group/IndustrialReference";
    }
}
