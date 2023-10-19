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
import com.aibig.umk.model.User.Internship;
import com.aibig.umk.services.InternshipService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/program")
public class ProgramController {
    private final InternshipService internshipService;

    @Autowired
    public ProgramController(InternshipService internshipService) {
        this.internshipService = internshipService;
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

    @GetMapping("/internships")
    public String getAllInternships(Model model) {
        List<Internship> internships = internshipService.getAllInternships();
        model.addAttribute("internships", internships);
        return "Programs/InternshipProgram"; // The HTML template name
    }

    @GetMapping("/add-internship")
    public String showAddInternshipForm(Model model) {
        model.addAttribute("internship", new Internship());
        return "Programs/add-internship"; // The HTML template for adding an internship
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
        return "redirect:/program/internships";
    }

}
