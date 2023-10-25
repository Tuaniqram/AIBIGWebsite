package com.aibig.umk.controller.Admin;

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
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final InternshipService internshipService;
    private final ProgramService programService;

    @Autowired
    public AdminController(InternshipService internshipService, ProgramService programService) {
        this.internshipService = internshipService;
        this.programService = programService;
    }

    @GetMapping("/add-internship")
    public String showAddInternshipForm(Model model) {
        model.addAttribute("internship", new Internship());
        return "Admin/add-internship"; // The HTML template for adding an internship
    }

    @PostMapping("/add-internship")
    public String addInternship(@ModelAttribute Internship internship,
            @RequestParam("imageFile") MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                byte[] imageBytes = imageFile.getBytes();
                internship.setImage(imageBytes);
            } catch (IOException e) {
                // Handle the exception
            }
        }

        internshipService.saveInternship(internship);
        return "redirect:/Admin/internships";
    }

    @GetMapping("/add-program")
    public String showAddProgramForm(Model model) {
        model.addAttribute("program", new Program());
        return "Admin/add-program"; // Display the HTML form to add a program
    }

    @PostMapping("/add-program")
    public String addProgram(@ModelAttribute Program program, @RequestParam("imageFile") MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                byte[] imageBytes = imageFile.getBytes();
                program.setProgramImage(imageBytes);
            } catch (IOException e) {
                // Handle the exception
            }
        }
        programService.saveProgram(program);
        return "redirect:/admin/add-program";
    }
}
