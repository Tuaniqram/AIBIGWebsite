package com.aibig.umk.controller.Group;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aibig.umk.model.User.Academic;
import com.aibig.umk.model.User.Adminstrative;
import com.aibig.umk.model.User.ResearchMember;
import com.aibig.umk.services.User.AcademicService;
import com.aibig.umk.services.User.AdminstrativeService;
import com.aibig.umk.services.User.ResearchMemberService;

@Controller
@RequestMapping("/group")
public class groupController {

    private final ResearchMemberService researchMemberService;
    private final AcademicService AcademicService;
    private final AdminstrativeService adminstrativeService;

    @Autowired
    public groupController(ResearchMemberService researchMemberService, AcademicService AcademicService,
            AdminstrativeService adminstrativeService) {
        this.researchMemberService = researchMemberService;
        this.AcademicService = AcademicService;
        this.adminstrativeService = adminstrativeService;
    }

    @GetMapping("/team")
    public String team(Model model) {
        model.addAttribute("breadcumbs1", "Group");
        model.addAttribute("breadcumbs2", "Team");

        List<Academic> academics = AcademicService.getAllAcademics();
        List<Adminstrative> admins = adminstrativeService.getAllAdmins();

        Academic director = new Academic();
        List<Academic> fellows = new ArrayList<>();
        int count = 0;

        Adminstrative officer = new Adminstrative();
        List<Adminstrative> Assistant = new ArrayList<>();

        for (Academic academic : academics) {
            if (academic.getAcademicRole().equals("Director")) {
                director = academic;
                model.addAttribute("director", director);
                break;
            }

            else if (academic.getAcademicRole().equals("AIBIG Fellow")) {
                fellows.add(academic);
                count++;
                if (count == 5)
                    break;
            }

        }

        for (Adminstrative admin : admins) {
            if (admin.getAdminRole().equals("Officer")) {
                officer = admin;
                model.addAttribute("officer", officer);
                break;
            }

            else if (admin.getAdminRole().equals("Assistant")) {
                Assistant.add(admin);
            }

        }

        model.addAttribute("fellows", fellows);
        model.addAttribute("Assistant", Assistant);
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
