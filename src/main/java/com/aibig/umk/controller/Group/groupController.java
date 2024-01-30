package com.aibig.umk.controller.Group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aibig.umk.model.Directory.Competition;
import com.aibig.umk.model.Directory.IndustrialReference;
import com.aibig.umk.model.Directory.ScientificAdvisory;
import com.aibig.umk.model.Directory.VisitingFellow;
import com.aibig.umk.model.User.Academic;
import com.aibig.umk.model.User.Adminstrative;
import com.aibig.umk.model.User.ResearchMember;
import com.aibig.umk.services.Directory.IndustrialReferenceService;
import com.aibig.umk.services.Directory.ScientificAdvisoryService;
import com.aibig.umk.services.Directory.VisitingFellowService;
import com.aibig.umk.services.User.AcademicService;
import com.aibig.umk.services.User.AdminstrativeService;
import com.aibig.umk.services.User.ResearchMemberService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/group")
public class groupController {

    private final ResearchMemberService researchMemberService;
    private final AcademicService AcademicService;
    private final AdminstrativeService adminstrativeService;
    private final ScientificAdvisoryService scientificAdvisoryService;
    private final IndustrialReferenceService industrialReferenceService;
    private final VisitingFellowService visitingFellowService;

    @GetMapping("/displayImage")
    @ResponseBody
    public ResponseEntity<byte[]> displayImage(@RequestParam("imageID") int imageID,
            @RequestParam("type") String type, @RequestParam("imagenom") int id) {
        if (type.equals("academic") && id == 1) {
            Academic academic = AcademicService.getAcademicById(imageID);
            if (academic != null && academic.getAcademicImage1() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(academic.getAcademicImage1(), headers, HttpStatus.OK);
            }
        } else if (type.equals("academic") && id == 2) {
            Academic academic = AcademicService.getAcademicById(imageID);
            if (academic != null && academic.getAcademicImage2() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(academic.getAcademicImage2(), headers, HttpStatus.OK);
            }
        } else if (type.equals("adminstrative") && id == 1) {
            Adminstrative admin = adminstrativeService.getAdminById(imageID);
            if (admin != null && admin.getAdminImage1() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(admin.getAdminImage1(), headers, HttpStatus.OK);
            }
        } else if (type.equals("adminstrative") && id == 2) {
            Adminstrative admin = adminstrativeService.getAdminById(imageID);
            if (admin != null && admin.getAdminImage2() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(admin.getAdminImage2(), headers, HttpStatus.OK);
            }
        } else if (type.equals("research")) {
            ResearchMember research = researchMemberService.getResearchMemberById(imageID);
            if (research != null && research.getResearchMemberImage() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(research.getResearchMemberImage(), headers, HttpStatus.OK);
            }
        } else if (type.equals("visiting")) {
            VisitingFellow visitingFellow = visitingFellowService.findByVisitingFellowId(imageID);
            if (visitingFellow != null && visitingFellow.getVisitingFellowImage() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(visitingFellow.getVisitingFellowImage(), headers, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/team")
    public String team(Model model) {
        model.addAttribute("titlePage", "TEAM");
        model.addAttribute("breadcumbs1", "Group");
        model.addAttribute("breadcumbs2", "Team");

        List<Academic> academics = AcademicService.getAllAcademics();
        List<Adminstrative> admins = adminstrativeService.getAllAdmins();

        Academic directortemp = new Academic();
        List<Academic> fellows = new ArrayList<>();
        int count = 0;

        Adminstrative officer = new Adminstrative();
        List<Adminstrative> Assistant = new ArrayList<>();

        for (Academic academic : academics) {
            if (academic.getAcademicRole().equals("Director")) {
                directortemp = academic;
            }

            else if (academic.getAcademicRole().equals("AIBIG Fellow")) {
                fellows.add(academic);
                count++;
                if (count == 5)
                    break;
            }

        }

        count = 0;

        for (Adminstrative admin : admins) {
            if (admin.getAdminRole().equals("Assistant Administrative Officer")) {
                officer = admin;
            }

            else if (admin.getAdminRole().equals("Administrative Assistant")) {
                Assistant.add(admin);
                count++;
                if (count == 5)
                    break;
            }

        }
        model.addAttribute("officer", officer);
        model.addAttribute("director", directortemp);
        model.addAttribute("fellows", fellows);
        model.addAttribute("Assistants", Assistant);
        return "Group/Team";
    }

    @GetMapping("/researchmember")
    public String researchmember(Model model) {
        // List<ResearchMember> researchers =
        // researchMemberService.getAllResearchMembers();
        // model.addAttribute("researchers", researchers);
        List<ResearchMember> AIresearchMembers = researchMemberService
                .getAllResearchMemberByResearchMemberCategory("ARTIFICIAL INTELLIGENCE");
        List<ResearchMember> BDresearchMembers = researchMemberService
                .getAllResearchMemberByResearchMemberCategory("BIG DATA");
        List<ResearchMember> IOTresearchMembers = researchMemberService
                .getAllResearchMemberByResearchMemberCategory("INTERNET OF THINGS");

        model.addAttribute("AIresearchMembers", AIresearchMembers);
        model.addAttribute("BDresearchMembers", BDresearchMembers);
        model.addAttribute("IOTresearchMembers", IOTresearchMembers);
        model.addAttribute("titlePage", "RESEARCH MEMBER");
        model.addAttribute("breadcumbs1", "Group");
        model.addAttribute("breadcumbs2", "Research Member");
        return "Group/ResearchMember";
    }

    @GetMapping("/scientific-advisory")
    public String scientificAdvisory(Model model) {
        model.addAttribute("titlePage", "SCIENTIFIC ADVISORY");
        model.addAttribute("breadcumbs1", "Group");
        model.addAttribute("breadcumbs2", "Scientific Advisory");

        List<ScientificAdvisory> allScientificAdvisories = scientificAdvisoryService.getAllScientificAdvisories();
        model.addAttribute("scientificAdvisory", allScientificAdvisories);
        return "Group/ScientificAdvisory";
    }

    @GetMapping("/reference-committee")
    public String referenceCommittee(Model model) {
        model.addAttribute("titlePage", "REFERENCE COMMITTEE");
        model.addAttribute("breadcumbs1", "Group");
        model.addAttribute("breadcumbs2", "Reference Committee");

        List<IndustrialReference> allIndustrialReferences = industrialReferenceService.findAllIndustrialReference();
        model.addAttribute("industrialReference", allIndustrialReferences);

        return "Group/IndustrialReference";
    }

    @GetMapping("/visiting-fellow")
    public String visitingFellow(Model model) {
        model.addAttribute("titlePage", "VISITING FELLOW/PROFESSOR");
        model.addAttribute("breadcumbs1", "Group");
        model.addAttribute("breadcumbs2", "Visiting Fellow/Professor");

        List<VisitingFellow> allVisitingFellows = visitingFellowService.findAllVisitingFellow();
        model.addAttribute("visitingFellow", allVisitingFellows);
        return "Group/VisitingFellow";
    }
}
