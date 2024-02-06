package com.aibig.umk.controller.Programs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.aibig.umk.model.Directory.Competition;
import com.aibig.umk.model.Directory.Programs;
import com.aibig.umk.model.User.Internship;
import com.aibig.umk.services.Directory.CompetitionService;
import com.aibig.umk.services.Directory.ProgramService;
import com.aibig.umk.services.User.InternshipService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/program")
public class ProgramController {
    private final InternshipService internshipService;
    private final ProgramService programService;
    private final CompetitionService competitionService;

    @Autowired
    public ProgramController(InternshipService internshipService, ProgramService programService,
            CompetitionService competitionService) {
        this.internshipService = internshipService;
        this.programService = programService;
        this.competitionService = competitionService;
    }

    @GetMapping("/displayImage")
    @ResponseBody
    public ResponseEntity<byte[]> displayImage(@RequestParam("internId") int internId) {
        Internship internship = internshipService.getInternshipById(internId);

        if (internship != null && internship.getImage() != null) {
            System.out.println("Image found");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

            return new ResponseEntity<>(internship.getImage(), headers, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/internship")
    public String getAllInternships(Model model) {
        model.addAttribute("titlePage", "INTERSHIP PROGRAM");
        model.addAttribute("breadcumbs1", "Programs");
        model.addAttribute("breadcumbs2", "Internship Team");
        List<Internship> internships = internshipService.getAllInternships();
        model.addAttribute("internships", internships);
        return "Programs/InternshipProgram"; // The HTML template name
    }

    @GetMapping("/post-grad")
    public String getAllPostGradPrograms(Model model) {
        model.addAttribute("titlePage", "POST GRADUATE PROGRAM");
        model.addAttribute("breadcumbs1", "Programs");
        model.addAttribute("breadcumbs2", "Post Graduate Programs");
        return "Programs/PostGradProgram";
    }

    @GetMapping("post-doc")
    public String getAllPostDocPrograms(Model model) {
        model.addAttribute("titlePage", "POST DOCTORAL PROGRAM");
        model.addAttribute("breadcumbs1", "Programs");
        model.addAttribute("breadcumbs2", "Post Doctoral Programs");
        return "Programs/PostDocProgram";
    }

    @GetMapping("/competition")
    public String getCompetition(Model model, @RequestParam("competitionShortName") String competitionShortName) {
        Competition competition = competitionService.getCompetitionByShortName(competitionShortName);
        model.addAttribute("titlePage", competition.getCompetitionName());
        model.addAttribute("breadcumbs1", "Programs");
        model.addAttribute("breadcumbs2", competition.getCompetitionName());
        model.addAttribute("competition", competition);
        return "Programs/CompetitionProgram";
    }

    @GetMapping("/shortcourse")
    public String getShortCourseProgramDetails(@RequestParam("programShortName") String programShortName, Model model) {

        List<Programs> allPrograms = programService.getAllPrograms();
        for (Programs program : allPrograms) {
            if (program.getProgramShortName().equals(programShortName)) {
                model.addAttribute("programs", program);
                model.addAttribute("titlePage", program.getProgramName());
                model.addAttribute("breadcumbs1", "Programs");
                model.addAttribute("breadcumbs2", program.getProgramShortName());

                break;
            }
        }

        return "Programs/ShortCourseProgram";
    }

    @GetMapping("/displayProgramImage")
    @ResponseBody
    public ResponseEntity<byte[]> displayProgramImage(@RequestParam("programId") int programId,
            @RequestParam("type") String type) {
        if (type.equals("shortcourse")) {
            Programs program = programService.getProgramByProgramId(programId);
            if (program != null && program.getProgramImage() != null) {
                System.out.println("Image found");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(program.getProgramImage(), headers, HttpStatus.OK);
            }
        } else if (type.equals("competition")) {
            Competition competition = competitionService.getCompetitionById(programId);
            if (competition != null && competition.getCompetitionImage() != null) {
                System.out.println("Image found");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(competition.getCompetitionImage(), headers, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("professional-certificate")
    public String ProfessionalCertificate(Model model) {
        model.addAttribute("titlePage", "PROFESSIONAL CERTIFICATE");
        model.addAttribute("breadcumbs1", "Programs");
        model.addAttribute("breadcumbs2", "Professional Certificate");
        return "Programs/Professional-Certificate";
    }

    @GetMapping("university-ir4")
    public String UniversityIR4(Model model) {

        model.addAttribute("titlePage", "UNIVERSITY IR4.0 COURSES");
        model.addAttribute("breadcumbs1", "Programs");
        model.addAttribute("breadcumbs2", "University IR4.0");
        return "Programs/University-IR4";
    }

}
