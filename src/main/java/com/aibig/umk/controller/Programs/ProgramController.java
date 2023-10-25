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

import com.aibig.umk.model.Directory.Program;
import com.aibig.umk.model.User.Internship;
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

    @Autowired
    public ProgramController(InternshipService internshipService, ProgramService programService) {
        this.internshipService = internshipService;
        this.programService = programService;
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
        model.addAttribute("breadcumbs1", "Program");
        model.addAttribute("breadcumbs2", "Internship Team");
        List<Internship> internships = internshipService.getAllInternships();
        model.addAttribute("internships", internships);
        return "Programs/InternshipProgram"; // The HTML template name
    }

    @GetMapping("/post-grad")
    public String getAllPostGradPrograms(Model model) {
        model.addAttribute("breadcumbs1", "Program");
        model.addAttribute("breadcumbs2", "Post Graduate Program");
        return "Programs/PostGradProgram";
    }

    @GetMapping("post-doc")
    public String getAllPostDocPrograms(Model model) {
        model.addAttribute("breadcumbs1", "Program");
        model.addAttribute("breadcumbs2", "Post Doctoral Program");
        return "Programs/PostDocProgram";
    }

    @GetMapping("/shortcourse")
    public String getShortCourseProgramDetails(@RequestParam("programShortName") String programShortName, Model model) {

        List<Program> programs = programService.getAllPrograms();
        for (Program program : programs) {
            if (program.getProgramShortName().equals(programShortName)) {
                String programDescription = program.getProgramDescription();
                List<String> programDesc = new ArrayList<>();

                String[] descriptionLines = programDescription.split("\\n");
                for (String line : descriptionLines) {
                    programDesc.add(line);
                }

                model.addAttribute("program", program);
                model.addAttribute("programDesc", programDesc);
                model.addAttribute("breadcumbs1", "Program");
                model.addAttribute("breadcumbs2", programShortName);
                return "Programs/ShortCourseProgram";
            }
        }
        // if null go back to home page
        return "redirect:/";
    }

    @GetMapping("/displayProgramImage")
    @ResponseBody
    public ResponseEntity<byte[]> displayProgramImage(@RequestParam("programId") int programId) {
        Program program = programService.getProgramByProgramId(programId);

        if (program != null && program.getProgramImage() != null) {
            System.out.println("Image found");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

            return new ResponseEntity<>(program.getProgramImage(), headers, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("professional-certificate")
    public String ProfessionalCertificate(Model model) {
        model.addAttribute("breadcumbs1", "Program");
        model.addAttribute("breadcumbs2", "Professional Certificate");
        return "Programs/Professional-Certificate";
    }

    @GetMapping("university-ir4")
    public String UniversityIR4(Model model) {
        model.addAttribute("breadcumbs1", "Program");
        model.addAttribute("breadcumbs2", "University IR4.0");
        return "Programs/University-IR4";
    }

}
