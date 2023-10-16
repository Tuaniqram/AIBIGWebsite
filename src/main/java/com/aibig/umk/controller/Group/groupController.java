package com.aibig.umk.controller.Group;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/group")
public class groupController {
    @GetMapping("/team")
    public String team() {
        return "Group/Team";
    }

    @GetMapping("/researchmember")
    public String researchmember() {
        return "Group/ResearchMember";
    }

    @GetMapping("/scientific-advisory")
    public String scientificAdvisory() {
        return "Group/ScientificAdvisory";
    }

    @GetMapping("/reference-committee")
    public String referenceCommittee() {
        return "Group/IndustrialReference";
    }
}
