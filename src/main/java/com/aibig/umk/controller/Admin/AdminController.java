package com.aibig.umk.controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.aibig.umk.model.Directory.Grantt;
import com.aibig.umk.model.Directory.News;
import com.aibig.umk.model.Directory.Program;
import com.aibig.umk.model.Directory.Publication;
import com.aibig.umk.model.User.Academic;
import com.aibig.umk.model.User.Adminstrative;
import com.aibig.umk.model.User.Internship;
import com.aibig.umk.model.User.ResearchMember;
import com.aibig.umk.services.Directory.GranttService;
import com.aibig.umk.services.Directory.NewsService;
import com.aibig.umk.services.Directory.ProgramService;
import com.aibig.umk.services.Directory.PublicationService;
import com.aibig.umk.services.User.AcademicService;
import com.aibig.umk.services.User.AdminstrativeService;
import com.aibig.umk.services.User.InternshipService;
import com.aibig.umk.services.User.ResearchMemberService;

import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final InternshipService internshipService;
    private final ProgramService programService;
    private final GranttService granttService;
    private final ResearchMemberService researchMemberRepository;
    private final AcademicService academicService;
    private final AdminstrativeService adminService;
    private final NewsService newsService;
    private final PublicationService publicationService;

    @Autowired
    public AdminController(InternshipService internshipService, ProgramService programService,
            GranttService granttService, ResearchMemberService researchMemberRepository,
            AcademicService academicService, AdminstrativeService adminService, NewsService newsService,
            PublicationService publicationService) {
        this.internshipService = internshipService;
        this.programService = programService;
        this.granttService = granttService;
        this.researchMemberRepository = researchMemberRepository;
        this.academicService = academicService;
        this.adminService = adminService;
        this.newsService = newsService;
        this.publicationService = publicationService;

    }

    @GetMapping("/logut")
    public String logout(HttpSession session, Model model) {
        model.addAttribute("error", null);
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/adminlogin")
    public String showAdminLogin(Model model, HttpSession session) {
        // Clear any previous error messages
        if (session.getAttribute("user") != null)
            return "redirect:/admin/dashboard";
        model.addAttribute("error", null);
        return "Admin/Login";
    }

    @PostMapping("/adminlogin")
    public String adminLogin(@RequestParam("Username") String username, @RequestParam("Password") String password,
            Model model, HttpSession session) {
        List<Academic> academics = academicService.getAllAcademics();
        List<Adminstrative> admins = adminService.getAllAdmins();

        boolean success = false;

        for (Academic academic : academics) {
            if (academic.getAcademicUsername().equals(username) && academic.getAcademicPassword().equals(password)) {
                model.addAttribute("user", academic);
                session.setAttribute("user", academic);
                success = true;
                return "redirect:/admin/dashboard";
            }
        }
        for (Adminstrative adminstrative : admins) {
            if (adminstrative.getAdminUsername().equals(username)
                    && adminstrative.getAdminPassword().equals(password)) {
                model.addAttribute("user", adminstrative);
                session.setAttribute("user", adminstrative);
                success = true;
                return "redirect:/admin/dashboard";
            }
        }
        if (success == false) {
            model.addAttribute("error", "Invalid Username or Password");
            System.out.println("error");
        }

        return "redirect:/admin/adminlogin";
    }

    @GetMapping("/monthly-publication-count")
    @ResponseBody
    public Map<Integer, Long> getMonthlyPublicationCount() {
        return publicationService.getMonthlyPublicationCount();
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin/adminlogin";

        List<Internship> internshipList = internshipService.getAllInternships();
        List<Publication> publicationsList = publicationService.getAllPublication();
        List<Grantt> grantsList = granttService.getAllGrantts();
        List<Program> programsList = programService.getAllPrograms();

        model.addAttribute("totalInternship", internshipList.size());
        model.addAttribute("totalPublication", publicationsList.size());
        model.addAttribute("totalGrant", grantsList.size());
        model.addAttribute("totalProgram", programsList.size());

        Academic tempAcademic;
        Adminstrative tempAdminstrative;

        if (session.getAttribute("user").getClass().equals(Academic.class)) {
            tempAcademic = (Academic) session.getAttribute("user");
            model.addAttribute("user", tempAcademic);
        } else {
            tempAdminstrative = (Adminstrative) session.getAttribute("user");
            model.addAttribute("user", tempAdminstrative);
        }
        return "Admin/Dashboard";
    }

    @GetMapping("/add-internship")
    public String showAddInternshipForm(Model model) {
        model.addAttribute("internship", new Internship());
        return "Admin/add-internship"; // The HTML template for adding an internship
    }

    @PostMapping("/add-internship")
    public String addInternship(@ModelAttribute Internship internship,
            @RequestParam("imageFile") MultipartFile imageFile) {
        internship.setImage(setimageinDB(imageFile));

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
        program.setProgramImage(setimageinDB(imageFile));
        programService.saveProgram(program);
        return "redirect:/admin/add-program";
    }

    @GetMapping("/add-grantt")
    public String showAddGranttForm(Model model) {
        // Retrieve a list of research member names from your service
        List<ResearchMember> researchMembers = researchMemberRepository.getAllResearchMembers();

        // Create a new Grantt object to bind to the form
        Grantt grantt = new Grantt();

        model.addAttribute("researchMembers", researchMembers);
        model.addAttribute("grantt", grantt);

        return "Admin/add-grantt"; // Create a new HTML template for the form
    }

    @PostMapping("/add-grantt")
    public String addGrantt(@ModelAttribute Grantt grantt) {
        // Process the form submission and save the grantt object
        // You'll need to implement this logic
        granttService.saveGrantt(grantt);

        return "redirect:/admin/add-grantt";
    }

    @GetMapping("/add-researchMember")
    public String showAddResearchMemberForm(Model model) {
        model.addAttribute("researchMember", new ResearchMember());
        return "Admin/add-researchMember"; // The HTML template for adding a research member
    }

    @PostMapping("/add-researchMember")
    public String addResearchMember(@ModelAttribute ResearchMember researchMember,
            @RequestParam("imageFile") MultipartFile imageFile) {
        researchMember.setResearchMemberImage(setimageinDB(imageFile));

        researchMemberRepository.saveResearchMember(researchMember);
        return "redirect:/admin/add-researchMember";
    }

    @GetMapping("/add-news")
    public String showAddNewsForm(Model model) {
        model.addAttribute("news", new News());
        return "Admin/add-news"; // The HTML template for adding a news
    }

    @PostMapping("/add-news")
    public String addNews(@ModelAttribute News news, @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam(name = "image1", required = false) MultipartFile image1,
            @RequestParam(name = "image2", required = false) MultipartFile image2,
            @RequestParam(name = "image3", required = false) MultipartFile image3,
            @RequestParam(name = "image4", required = false) MultipartFile image4) {

        news.setPrimaryimage(setimageinDB(imageFile));
        news.setImage1(image1 != null ? setimageinDB(image1) : null);
        news.setImage2(image2 != null ? setimageinDB(image2) : null);
        news.setImage3(image3 != null ? setimageinDB(image3) : null);
        news.setImage4(image4 != null ? setimageinDB(image4) : null);

        System.out.println("Hello");

        newsService.saveNews(news);
        return "redirect:/admin/dashboard";
    }

    public byte[] setimageinDB(MultipartFile tempfile) {
        byte[] imageBytes = null;
        if (tempfile != null && !tempfile.isEmpty()) {
            try {
                imageBytes = tempfile.getBytes();
            } catch (IOException e) {
                // Handle the exception
                e.printStackTrace(); // Log or handle the exception appropriately
            }
        }
        return imageBytes;
    }

}
