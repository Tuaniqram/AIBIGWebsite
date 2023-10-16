// package com.aibig.umk.controller.Programs;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.ui.Model;

// import com.aibig.umk.model.User.Internship;
// import com.aibig.umk.services.InternshipService;

// @Controller
// @RequestMapping("/program")
// public class ProgramController {

// private final InternshipService internshipService;

// @Autowired
// public ProgramController(InternshipService internshipService) {
// this.internshipService = internshipService;
// }

// @GetMapping("/internship")
// public String internship(Model model) {
// List<Internship> interns = internshipService.getAllInternships();
// model.addAttribute("interns", interns);
// return "Programs/InternshipProgram";
// }
// }
