package com.aibig.umk.controller.Group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aibig.umk.model.User.ResearchMember;
import com.aibig.umk.services.User.ResearchMemberService;

@Controller
@RequestMapping("/group")
public class groupController {

    private final ResearchMemberService researchMemberService;

    @Autowired
    public groupController(ResearchMemberService researchMemberService) {
        this.researchMemberService = researchMemberService;
    }

    @GetMapping("/team")
    public String team(Model model) {
        model.addAttribute("breadcumbs1", "Group");
        model.addAttribute("breadcumbs2", "Team");
        return "Group/Team";
    }

    @GetMapping("/researchmember")
    public String researchmember(Model model) {
        List<ResearchMember> researchers = researchMemberService.getAllResearchMembers();
        model.addAttribute("researchers", researchers);

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

    @GetMapping("/visiting-fellow")
    public String visitingFellow(Model model) {
        model.addAttribute("breadcumbs1", "Group");
        model.addAttribute("breadcumbs2", "Visiting Fellow/Professor");
        return "Group/VisitingFellow";
    }
}
