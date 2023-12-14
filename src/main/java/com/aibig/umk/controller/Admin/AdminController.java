package com.aibig.umk.controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.aibig.umk.model.Directory.*;
import com.aibig.umk.model.User.*;
import com.aibig.umk.services.Directory.*;
import com.aibig.umk.services.User.*;

import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class AdminController {

    // Directory Service
    private final CollabService collabService;
    private final CompetitionService competitionService;
    private final GranttService granttService;
    private final IndustrialReferenceService industrialReferenceService;
    private final MouMoaService mouMoaService;
    private final NewsService newsService;
    private final ProgramService programService;
    private final PublicationService publicationService;
    private final ResearchPaperService researchPaperService;
    private final ScientificAdvisoryService scientificAdvisoryService;
    private final VisitingFellowService visitingFellowService;

    // User Service
    private final AcademicService academicService;
    private final AdminstrativeService adminService;
    private final InternshipService internshipService;
    private final ResearchMemberService researchMemberService;

    // Constructor
    @Autowired
    public AdminController(CollabService collabService, CompetitionService competitionService,
            GranttService granttService, IndustrialReferenceService industrialReferenceService,
            MouMoaService mouMoaService, NewsService newsService, ProgramService programService,
            PublicationService publicationService, ResearchPaperService researchPaperService,
            ScientificAdvisoryService scientificAdvisoryService, VisitingFellowService visitingFellowService,
            AcademicService academicService, AdminstrativeService adminService, InternshipService internshipService,
            ResearchMemberService researchMemberService) {
        this.collabService = collabService;
        this.competitionService = competitionService;
        this.granttService = granttService;
        this.industrialReferenceService = industrialReferenceService;
        this.mouMoaService = mouMoaService;
        this.newsService = newsService;
        this.programService = programService;
        this.publicationService = publicationService;
        this.researchPaperService = researchPaperService;
        this.scientificAdvisoryService = scientificAdvisoryService;
        this.visitingFellowService = visitingFellowService;
        this.academicService = academicService;
        this.adminService = adminService;
        this.internshipService = internshipService;
        this.researchMemberService = researchMemberService;
    }

    // Main Functions

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {
        model.addAttribute("error", null);
        session.invalidate();
        return "redirect:/admin";
    }

    @GetMapping("")
    public String showAdminLogin(Model model, HttpSession session) {
        // Clear any previous error messages
        if (session.getAttribute("user") != null)
            return "redirect:/admin/dashboard";

        model.addAttribute("error", false);
        return "Admin/Login";
    }

    @PostMapping("")
    public String adminLogin(@RequestParam("Username") String username,
            @RequestParam("Password") String password,
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

        if (!success) {
            model.addAttribute("error", true);
            return "Admin/Login";
        }

        return "redirect:/admin";
    }

    @GetMapping("/monthly-publication-count")
    @ResponseBody
    public Map<Integer, Long> getMonthlyPublicationCount() {
        return publicationService.getMonthlyPublicationCount();
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";

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

        getModelType(model, session);
        return "Admin/Dashboard";
    }

    private void getModelType(Model model, HttpSession session) {
        Academic tempAcademic;
        Adminstrative tempAdminstrative;
        if (session.getAttribute("user").getClass().equals(Academic.class)) {
            tempAcademic = (Academic) session.getAttribute("user");
            model.addAttribute("userType", "academic");
            model.addAttribute("user", tempAcademic);
        } else {
            tempAdminstrative = (Adminstrative) session.getAttribute("user");
            model.addAttribute("userType", "admin");
            model.addAttribute("user", tempAdminstrative);
        }
    }

    // Adding New Data

    @GetMapping("/add-internship")
    public String showAddInternshipForm(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        model.addAttribute("internship", new Internship());
        return "Admin/AddingNewData/add-internship"; // The HTML template for adding an internship
    }

    @PostMapping("/add-internship")
    public String addInternship(@ModelAttribute Internship internship,
            @RequestParam("imageFile") MultipartFile imageFile, HttpSession session) {
        internship.setImage(setimageinDB(imageFile));

        internshipService.saveInternship(internship);
        return "redirect:/Admin/mainInternships";
    }

    @GetMapping("/add-program")
    public String showAddProgramForm(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        model.addAttribute("program", new Program());
        return "Admin/AddingNewData/add-program"; // Display the HTML form to add a program
    }

    @PostMapping("/add-program")
    public String addProgram(@ModelAttribute Program program, @RequestParam("imageFile") MultipartFile imageFile,
            HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        program.setProgramImage(setimageinDB(imageFile));
        programService.saveProgram(program);
        return "redirect:/admin/mainprograms";
    }

    @GetMapping("/add-grantt")
    public String showAddGranttForm(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        // Retrieve a list of research member names from your service
        List<ResearchMember> researchMembers = researchMemberService.getAllResearchMembers();

        // Create a new Grantt object to bind to the form
        Grantt grantt = new Grantt();

        model.addAttribute("researchMembers", researchMembers);
        model.addAttribute("grantt", grantt);

        return "Admin/AddingNewData/add-grantt"; // Create a new HTML template for the form
    }

    @PostMapping("/add-grantt")
    public String addGrantt(@ModelAttribute Grantt grantt, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        // Process the form submission and save the grantt object
        // You'll need to implement this logic
        granttService.saveGrantt(grantt);

        return "redirect:/admin/maingrants";
    }

    @GetMapping("/add-researchMember")
    public String showAddResearchMemberForm(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        model.addAttribute("researchMember", new ResearchMember());
        return "Admin/AddingNewData/add-researchMember"; // The HTML template for adding a research member
    }

    @PostMapping("/add-researchMember")
    public String addResearchMember(@ModelAttribute ResearchMember researchMember,
            @RequestParam("imageFile") MultipartFile imageFile, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        researchMember.setResearchMemberImage(setimageinDB(imageFile));

        researchMemberService.saveResearchMember(researchMember);
        return "redirect:/admin/add-researchMember";
    }

    @GetMapping("/add-news")
    public String showAddNewsForm(Model model, @RequestParam("newsCat") String newsCategory, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        model.addAttribute("newsCat", newsCategory);
        model.addAttribute("news", new News());
        return "Admin/AddingNewData/add-news"; // The HTML template for adding a news
    }

    @PostMapping("/add-news")
    public String addNews(@ModelAttribute News news, @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("newsCat") String newsCategory,
            HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";

        news.setPrimaryimage(setimageinDB(imageFile));
        news.setNewsCategory(newsCategory);

        newsService.saveNews(news);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/add-adminstrative")
    public String showAddForm(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        model.addAttribute("adminRoles", Arrays.asList("Assistant Administrative Officer", "Administrative Assistant"));
        model.addAttribute("admin", new Adminstrative());
        return "Admin/AddingNewData/add-Admin";
    }

    @PostMapping("/add-adminstrative")
    public String addAdminstrative(@ModelAttribute Adminstrative adminstrative,
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("SecondaryImageFile") MultipartFile secondaryImageFile, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        adminstrative.setAdminImage1(setimageinDB(imageFile));
        adminstrative.setAdminImage2(setimageinDB(secondaryImageFile));

        adminService.saveadmin(adminstrative);
        return "redirect:/admin/add-adminstrative";
    }

    @GetMapping("/add-academicstaff")
    public String showAddAcademicStaffForm(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        model.addAttribute("academicRoles", Arrays.asList("Director", "AIBIG Fellow"));
        model.addAttribute("departments", Arrays.asList("Director", "AIBIG Fellow"));
        model.addAttribute("academic", new Academic());
        return "Admin/AddingNewData/add-Academic";
    }

    @PostMapping("/add-academicstaff")
    public String addAcademicStaff(@ModelAttribute Academic academic,
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("SecondaryImageFile") MultipartFile secondaryImageFile, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        academic.setAcademicImage1(setimageinDB(imageFile));
        academic.setAcademicImage2(setimageinDB(secondaryImageFile));

        academicService.saveAcademic(academic);
        return "redirect:/admin/add-academicstaff";
    }

    @GetMapping("/add-collaboration")
    public String showAddCollaborationForm(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        getModelType(model, session);
        model.addAttribute("breadcrumbs1", "Directory");
        model.addAttribute("breadcrumbs2", "Add Collaborations");
        model.addAttribute("collaboration", new Collaborations());
        return "Admin/AddingNewData/add-collaboration"; // The HTML template for adding a collaboration
    }

    @PostMapping("/add-collaboration")
    public String addCollaboration(@ModelAttribute Collaborations collab, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        collabService.saveCollab(collab);
        return "redirect:/admin/maincollaborations";
    }

    @GetMapping("/add-competition")
    public String showAddCompetitionForm(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        getModelType(model, session);
        model.addAttribute("breadcrumbs1", "News and Events");
        model.addAttribute("breadcrumbs2", "Add Competitions");
        model.addAttribute("competition", new Competition());
        return "Admin/AddingNewData/add-competition"; // The HTML template for adding a competition
    }

    @PostMapping("/add-competition")
    public String addCompetition(@ModelAttribute Competition competition,
            @RequestParam("imageFile") MultipartFile imageFile, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        competition.setCompetitionImage(setimageinDB(imageFile));

        competitionService.saveCompetition(competition);
        return "redirect:/admin/maincompetitions";
    }

    @GetMapping("/add-industrialReference")
    public String showAddIndustrialReferenceForm(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        getModelType(model, session);
        model.addAttribute("breadcrumbs1", "Directory");
        model.addAttribute("breadcrumbs2", "Add Industrial References");
        model.addAttribute("industrialReference", new IndustrialReference());
        return "Admin/AddingNewData/add-industrialReference"; // The HTML template for adding a industrialReference
    }

    @PostMapping("/add-industrialReference")
    public String addIndustrialReference(@ModelAttribute IndustrialReference industrialReference, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        industrialReferenceService.saveIndustrialReference(industrialReference);
        return "redirect:/admin/mainindustrialreferences";
    }

    @GetMapping("/add-mou-moa")
    public String showAddMouMoaForm(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        getModelType(model, session);
        model.addAttribute("breadcrumbs1", "Directory");
        model.addAttribute("breadcrumbs2", "Add MOU/MOAs");
        model.addAttribute("mouMoa", new MouMoa());
        return "Admin/AddingNewData/add-mou-moa"; // The HTML template for adding a mouMoa
    }

    @PostMapping("/add-mou-moa")
    public String addMouMoa(@ModelAttribute MouMoa mouMoa, @RequestParam("imageFile") MultipartFile imageFile,
            HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        mouMoa.setMouMoaImage(setimageinDB(imageFile));

        mouMoaService.saveMouMoa(mouMoa);
        return "redirect:/admin/mainmoumoas";
    }

    @GetMapping("/add-publication")
    public String showAddPublicationForm(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        model.addAttribute("publicationTypes", Arrays.asList("ISI –Web of Science Indexed", "SCOPUS Indexed",
                "Proceedings / Conferences", "Book Chapter", "Book"));
        getModelType(model, session);
        model.addAttribute("breadcrumbs1", "Directory");
        model.addAttribute("breadcrumbs2", "Add Publications");
        model.addAttribute("publication", new Publication());
        return "Admin/AddingNewData/add-publication"; // The HTML template for adding a publication
    }

    @PostMapping("/add-publication")
    public String addPublication(@ModelAttribute Publication publication, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        publicationService.savePublication(publication);
        return "redirect:/admin/mainpublications";
    }

    @GetMapping("/add-researchpaper")
    public String showAddResearchPaperForm(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        getModelType(model, session);
        model.addAttribute("breadcrumbs1", "Directory");
        model.addAttribute("breadcrumbs2", "Add Research Papers");
        model.addAttribute("researchPaper", new ResearchPaper());
        return "Admin/AddingNewData/add-researchpaper"; // The HTML template for adding a researchPaper
    }

    @PostMapping("/add-researchpaper")
    public String addResearchPaper(@ModelAttribute ResearchPaper researchPaper,
            @RequestParam("imageFile") MultipartFile imageFile, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        researchPaper.setResearchPaperImage(setimageinDB(imageFile));

        researchPaperService.saveResearchPaper(researchPaper);
        return "redirect:/admin/mainresearchpapers";
    }

    @GetMapping("/add-scientificadvisory")
    public String showAddScientificAdvisoryForm(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        getModelType(model, session);
        model.addAttribute("breadcrumbs1", "Directory");
        model.addAttribute("breadcrumbs2", "Add Scientific Advisory");
        model.addAttribute("scientificAdvisory", new ScientificAdvisory());
        return "Admin/AddingNewData/add-scientificadvisory"; // The HTML template for adding a scientificAdvisory
    }

    @PostMapping("/add-scientificadvisory")
    public String addScientificAdvisory(@ModelAttribute ScientificAdvisory scientificAdvisory, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        scientificAdvisoryService.saveScientificAdvisory(scientificAdvisory);
        return "redirect:/admin/mainscientificadvisory";
    }

    @GetMapping("/add-visitingfellow")
    public String showAddVisitingFellowForm(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        model.addAttribute("visitingFellowRoles", Arrays.asList("Visiting Fellow", "Visiting Professor"));
        getModelType(model, session);
        model.addAttribute("breadcrumbs1", "Directory");
        model.addAttribute("breadcrumbs2", "Add Visiting Fellow");
        model.addAttribute("visitingFellow", new VisitingFellow());
        return "Admin/AddingNewData/add-visitingfellow"; // The HTML template for adding a visitingFellow
    }

    @PostMapping("/add-visitingfellow")
    public String addVisitingFellow(@ModelAttribute VisitingFellow visitingFellow,
            @RequestParam("imageFile") MultipartFile imageFile, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        visitingFellow.setVisitingFellowImage(setimageinDB(imageFile));

        visitingFellowService.saveVisitingFellow(visitingFellow);
        return "redirect:/admin/mainvisitingfellows";
    }

    // Main Page

    @GetMapping("/maincollaborations")
    public String showMainCollaborations(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        getModelType(model, session);
        model.addAttribute("breadcrumbs1", "Directory");
        model.addAttribute("breadcrumbs2", "Collaborations");
        List<Collaborations> collaborations = collabService.getAllCollabs();
        model.addAttribute("collaborations", collaborations);
        return "Admin/MainPage/maincollaborations";
    }

    @GetMapping("/maincompetitions")
    public String showMainCompetitions(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        getModelType(model, session);
        model.addAttribute("breadcrumbs1", "News and Events");
        model.addAttribute("breadcrumbs2", "Competitions");
        List<Competition> competitions = competitionService.getAllCompetitions();
        model.addAttribute("competitions", competitions);
        return "Admin/MainPage/maincompetitions";
    }

    @GetMapping("/maingrants")
    public String showMainGrants(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        getModelType(model, session);
        model.addAttribute("breadcrumbs1", "Directory");
        model.addAttribute("breadcrumbs2", "Grants");
        List<Grantt> grants = granttService.getAllGrantts();
        model.addAttribute("grants", grants);
        return "Admin/MainPage/maingrants";
    }

    @GetMapping("/mainindustrialreferences")
    public String showMainIndustrialReferences(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        getModelType(model, session);
        model.addAttribute("breadcrumbs1", "Directory");
        model.addAttribute("breadcrumbs2", "Industrial References");
        List<IndustrialReference> industrialReferences = industrialReferenceService.findAllIndustrialReference();
        model.addAttribute("industrialReferences", industrialReferences);
        return "Admin/MainPage/mainindustrialreferences";
    }

    @GetMapping("/mainmoumoas")
    public String showMainMouMoas(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        getModelType(model, session);
        model.addAttribute("breadcrumbs1", "Directory");
        model.addAttribute("breadcrumbs2", "MOU/MOAs");
        List<MouMoa> mouMoas = mouMoaService.findAllMouMoa();
        model.addAttribute("mouMoas", mouMoas);
        return "Admin/MainPage/mainmoumoas";
    }

    @GetMapping("/mainnews")
    public String showMainNews(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        getModelType(model, session);
        model.addAttribute("breadcrumbs1", "News and Events");
        model.addAttribute("breadcrumbs2", "News");
        List<News> Allnews = newsService.getAllNews();
        List<News> tempNews = new ArrayList<>();
        List<News> tempMeeting = new ArrayList<>();
        List<News> tempSeminar = new ArrayList<>();
        List<News> tempConferences = new ArrayList<>();
        List<News> tempForums = new ArrayList<>();

        // News, Meeting, seminar, conferences, forums as values
        for (News n : Allnews) {
            switch (n.getNewsCategory()) {
                case "News":
                    tempNews.add(n);
                    break;
                case "Meeting":
                    tempMeeting.add(n);
                    break;
                case "Seminar":
                    tempSeminar.add(n);
                    break;
                case "Conferences":
                    tempConferences.add(n);
                    break;
                case "Forums":
                    tempForums.add(n);
                    break;
            }
        }
        model.addAttribute("news", tempNews);
        model.addAttribute("meetings", tempMeeting);
        model.addAttribute("seminars", tempSeminar);
        model.addAttribute("conferences", tempConferences);
        model.addAttribute("forums", tempForums);

        return "Admin/MainPage/mainnews";
    }

    @GetMapping("/mainprograms")
    public String showMainPrograms(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        getModelType(model, session);
        model.addAttribute("breadcrumbs1", "Directory");
        model.addAttribute("breadcrumbs2", "Programs");
        List<Program> programs = programService.getAllPrograms();
        model.addAttribute("programs", programs);
        return "Admin/MainPage/mainprograms";
    }

    @GetMapping("/mainpublications")
    public String showMainPublications(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        getModelType(model, session);
        List<Publication> publications = publicationService.getAllPublication();
        model.addAttribute("publications", publications);
        return "Admin/MainPage/mainpublications";
    }

    @GetMapping("/mainresearchpapers")
    public String showMainResearchPapers(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        getModelType(model, session);
        List<ResearchPaper> researchPapers = researchPaperService.getAllResearchPapers();
        model.addAttribute("researchPapers", researchPapers);
        return "Admin/MainPage/mainresearchpapers";
    }

    @GetMapping("/mainscientificadvisory")
    public String showMainScientificAdvisory(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        getModelType(model, session);
        List<ScientificAdvisory> scientificAdvisories = scientificAdvisoryService.getAllScientificAdvisories();
        model.addAttribute("scientificAdvisories", scientificAdvisories);
        return "Admin/MainPage/mainscientificadvisory";
    }

    @GetMapping("/mainvisitingfellows")
    public String showMainVisitingFellows(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        getModelType(model, session);
        List<VisitingFellow> visitingFellows = visitingFellowService.findAllVisitingFellow();
        model.addAttribute("visitingFellows", visitingFellows);
        return "Admin/MainPage/mainvisitingfellows";
    }

    @GetMapping("/mainAcademics")
    public String showMainAcademics(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        getModelType(model, session);
        List<Academic> academics = academicService.getAllAcademics();
        model.addAttribute("academics", academics);
        return "Admin/MainPage/mainAcademics";
    }

    @GetMapping("/mainAdmins")
    public String showMainAdmins(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        getModelType(model, session);
        List<Adminstrative> admins = adminService.getAllAdmins();
        model.addAttribute("admins", admins);
        return "Admin/MainPage/mainAdmins";
    }

    @GetMapping("/mainInternships")
    public String showMainInternships(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        getModelType(model, session);
        List<Internship> internships = internshipService.getAllInternships();
        model.addAttribute("internships", internships);
        return "Admin/MainPage/mainInternships";
    }

    @GetMapping("/mainResearchMembers")
    public String showMainResearchMembers(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        getModelType(model, session);
        List<ResearchMember> researchMembers = researchMemberService.getAllResearchMembers();
        model.addAttribute("researchMembers", researchMembers);
        return "Admin/MainPage/mainResearchMembers";
    }

    // UpdatePage

    @GetMapping("/update-collaboration")
    public String showUpdateCollaborationForm(Model model, @RequestParam("collabId") int collabId,
            HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        Collaborations collab = collabService.getCollabById(collabId);
        model.addAttribute("collab", collab);
        return "Admin/UpdatePage/update-collaboration"; // The HTML template for updating a collaboration
    }

    @PostMapping("/update-collaboration")
    public String updateCollaboration(@ModelAttribute Collaborations collab, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";

        collabService.updateCollab(collab);
        return "redirect:/admin/maincollaborations";
    }

    @GetMapping("/update-competition")
    public String showUpdateCompetitionForm(Model model, @RequestParam("competitionId") int competitionId,
            HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        Competition competition = competitionService.getCompetitionById(competitionId);
        model.addAttribute("competition", competition);
        return "Admin/UpdatePage/update-competition"; // The HTML template for updating a competition
    }

    @PostMapping("/update-competition")
    public String updateCompetition(@ModelAttribute Competition competition,
            @RequestParam("imageFile") MultipartFile imageFile, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";

        if (imageFile != null && !imageFile.isEmpty() && StringUtils.hasText(imageFile.getOriginalFilename())) {
            competition.setCompetitionImage(setimageinDB(imageFile));
        } else {
            Competition existingCompetition = competitionService.getCompetitionById(competition.getCompetitionId());
            competition.setCompetitionImage(existingCompetition.getCompetitionImage());
        }

        competitionService.updateCompetition(competition);
        return "redirect:/admin/maincompetitions";
    }

    @GetMapping("/update-grantt")
    public String showUpdateGranttForm(Model model, @RequestParam("granttId") int granttId, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        Grantt grantt = granttService.getGranttById(granttId);
        model.addAttribute("grantt", grantt);
        return "Admin/UpdatePage/update-grantt"; // The HTML template for updating a grantt
    }

    @PostMapping("/update-grantt")
    public String updateGrantt(@ModelAttribute Grantt grantt, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        granttService.updateGrantt(grantt);
        return "redirect:/admin/maingrants";
    }

    @GetMapping("/update-industrialReference")
    public String showUpdateIndustrialReferenceForm(Model model,
            @RequestParam("industrialReferenceId") int industrialReferenceId, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        IndustrialReference industrialReference = industrialReferenceService
                .findByIndustrialReferenceId(industrialReferenceId);
        model.addAttribute("industrialReference", industrialReference);
        return "Admin/UpdatePage/update-industrialReference"; // The HTML template for updating a industrialReference
    }

    @PostMapping("/update-industrialReference")
    public String updateIndustrialReference(@ModelAttribute IndustrialReference industrialReference,
            HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        industrialReferenceService.updateIndustrialReference(industrialReference);
        return "redirect:/admin/mainindustrialreferences";
    }

    @GetMapping("/update-mou-moa")
    public String showUpdateMouMoaForm(Model model, @RequestParam("mouMoaId") int mouMoaId, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        MouMoa mouMoa = mouMoaService.findByMouMoaId(mouMoaId);
        model.addAttribute("mouMoa", mouMoa);
        return "Admin/UpdatePage/update-mou-moa"; // The HTML template for updating a mouMoa
    }

    @PostMapping("/update-mou-moa")
    public String updateMouMoa(@ModelAttribute MouMoa mouMoa, @RequestParam("imageFile") MultipartFile imageFile,
            HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";

        if (imageFile != null && !imageFile.isEmpty() && StringUtils.hasText(imageFile.getOriginalFilename())) {
            mouMoa.setMouMoaImage(setimageinDB(imageFile));
        } else {
            MouMoa existingMouMoa = mouMoaService.findByMouMoaId(mouMoa.getMouMoaId());
            mouMoa.setMouMoaImage(existingMouMoa.getMouMoaImage());

        }

        mouMoaService.updateMouMoa(mouMoa);
        return "redirect:/admin/mainmoumoas";
    }

    @GetMapping("/update-news")
    public String showUpdateNewsForm(Model model, @RequestParam("newsId") int newsId, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        News news = newsService.getNewsById(newsId);
        model.addAttribute("news", news);
        return "Admin/UpdatePage/update-news"; // The HTML template for updating a news
    }

    @PostMapping("/update-news")
    public String updateNews(@ModelAttribute News news, @RequestParam("imageFile") MultipartFile imageFile,
            HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        if (imageFile != null && !imageFile.isEmpty() && StringUtils.hasText(imageFile.getOriginalFilename())) {
            news.setPrimaryimage(setimageinDB(imageFile));
        } else {
            News existingNews = newsService.getNewsById(news.getNewsId());
            news.setPrimaryimage(existingNews.getPrimaryimage());
        }

        newsService.updateNews(news);
        return "redirect:/admin/mainnews";
    }

    @GetMapping("/update-program")
    public String showUpdateProgramForm(Model model, @RequestParam("programId") int programId, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        Program program = programService.getProgramById(programId);
        model.addAttribute("program", program);
        return "Admin/UpdatePage/update-program"; // The HTML template for updating a program
    }

    @PostMapping("/update-program")
    public String updateProgram(@ModelAttribute Program program, @RequestParam("imageFile") MultipartFile imageFile,
            HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";

        if (imageFile != null && !imageFile.isEmpty() && StringUtils.hasText(imageFile.getOriginalFilename())) {
            program.setProgramImage(setimageinDB(imageFile));
        } else {
            Program existingProgram = programService.getProgramById(program.getProgramId());
            program.setProgramImage(existingProgram.getProgramImage());
        }

        programService.updateProgram(program);
        return "redirect:/admin/mainprograms";
    }

    @GetMapping("/update-publication")
    public String showUpdatePublicationForm(Model model, @RequestParam("publicationId") int publicationId,
            HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        Publication publication = publicationService.getPublicationById(publicationId);
        model.addAttribute("publication", publication);
        return "Admin/UpdatePage/update-publication"; // The HTML template for updating a publication
    }

    @PostMapping("/update-publication")
    public String updatePublication(@ModelAttribute Publication publication, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        publicationService.savePublication(publication);
        return "redirect:/admin/mainpublications";
    }

    @GetMapping("/update-researchpaper")
    public String showUpdateResearchPaperForm(Model model, @RequestParam("researchPaperId") int researchPaperId,
            HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        ResearchPaper researchPaper = researchPaperService.getResearchPaperById(researchPaperId);
        model.addAttribute("researchPaper", researchPaper);
        return "Admin/UpdatePage/update-researchpaper"; // The HTML template for updating a researchPaper
    }

    @PostMapping("/update-researchpaper")
    public String updateResearchPaper(@ModelAttribute ResearchPaper researchPaper,
            @RequestParam("imageFile") MultipartFile imageFile, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";

        if (imageFile != null && !imageFile.isEmpty() && StringUtils.hasText(imageFile.getOriginalFilename())) {
            researchPaper.setResearchPaperImage(setimageinDB(imageFile));
        } else {
            ResearchPaper existingResearchPaper = researchPaperService
                    .getResearchPaperById(researchPaper.getResearchPaperId());
            researchPaper.setResearchPaperImage(existingResearchPaper.getResearchPaperImage());
        }

        researchPaperService.saveResearchPaper(researchPaper);
        return "redirect:/admin/mainresearchpapers";
    }

    @GetMapping("/update-scientificadvisory")
    public String showUpdateScientificAdvisoryForm(Model model,
            @RequestParam("scientificAdvisoryId") int scientificAdvisoryId, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        ScientificAdvisory scientificAdvisory = scientificAdvisoryService
                .getScientificAdvisoryById(scientificAdvisoryId);
        model.addAttribute("scientificAdvisory", scientificAdvisory);
        return "Admin/UpdatePage/update-scientificadvisory"; // The HTML template for updating a scientificAdvisory
    }

    @PostMapping("/update-scientificadvisory")
    public String updateScientificAdvisory(@ModelAttribute ScientificAdvisory scientificAdvisory, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        scientificAdvisoryService.saveScientificAdvisory(scientificAdvisory);
        return "redirect:/admin/mainscientificadvisory";
    }

    @GetMapping("/update-visitingfellow")
    public String showUpdateVisitingFellowForm(Model model, @RequestParam("visitingFellowId") int visitingFellowId,
            HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        VisitingFellow visitingFellow = visitingFellowService.findByVisitingFellowId(visitingFellowId);
        model.addAttribute("visitingFellow", visitingFellow);
        return "Admin/UpdatePage/update-visitingfellow"; // The HTML template for updating a visitingFellow
    }

    @PostMapping("/update-visitingfellow")
    public String updateVisitingFellow(@ModelAttribute VisitingFellow visitingFellow,
            @RequestParam("imageFile") MultipartFile imageFile, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";

        if (imageFile != null && !imageFile.isEmpty() && StringUtils.hasText(imageFile.getOriginalFilename())) {
            visitingFellow.setVisitingFellowImage(setimageinDB(imageFile));
        } else {
            VisitingFellow existingVisitingFellow = visitingFellowService
                    .findByVisitingFellowId(visitingFellow.getVisitingFellowId());
            visitingFellow.setVisitingFellowImage(existingVisitingFellow.getVisitingFellowImage());
        }

        visitingFellowService.saveVisitingFellow(visitingFellow);
        return "redirect:/admin/mainvisitingfellows";
    }

    @GetMapping("/update-academic")
    public String showUpdateAcademicForm(Model model, @RequestParam("academicId") int academicId, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        Academic academic = academicService.getAcademicById(academicId);
        model.addAttribute("academic", academic);
        return "Admin/UpdatePage/update-academic"; // The HTML template for updating a academic
    }

    @PostMapping("/update-academic")
    public String updateAcademic(@ModelAttribute Academic academic, @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("SecondaryImageFile") MultipartFile secondaryImageFile, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        if (imageFile != null && !imageFile.isEmpty() && StringUtils.hasText(imageFile.getOriginalFilename())) {
            academic.setAcademicImage1(setimageinDB(imageFile));
        } else {
            Academic existingAcademic = academicService.getAcademicById(academic.getAcademicId());
            academic.setAcademicImage1(existingAcademic.getAcademicImage1());
        }

        if (secondaryImageFile != null && !secondaryImageFile.isEmpty()
                && StringUtils.hasText(secondaryImageFile.getOriginalFilename())) {
            academic.setAcademicImage2(setimageinDB(secondaryImageFile));
        } else {
            Academic existingAcademic = academicService.getAcademicById(academic.getAcademicId());
            academic.setAcademicImage2(existingAcademic.getAcademicImage2());
        }

        academicService.saveAcademic(academic);
        return "redirect:/admin/mainAcademics";
    }

    @GetMapping("/update-adminstrative")
    public String showUpdateAdminstrativeForm(Model model, @RequestParam("adminId") int adminId, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        Adminstrative adminstrative = adminService.getAdminById(adminId);
        model.addAttribute("adminstrative", adminstrative);
        return "Admin/UpdatePage/update-adminstrative"; // The HTML template for updating a adminstrative
    }

    @PostMapping("/update-adminstrative")
    public String updateAdminstrative(@ModelAttribute Adminstrative adminstrative,
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("SecondaryImageFile") MultipartFile secondaryImageFile, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        if (imageFile != null && !imageFile.isEmpty() && StringUtils.hasText(imageFile.getOriginalFilename())) {
            adminstrative.setAdminImage1(setimageinDB(imageFile));
        } else {
            Adminstrative existingAdminstrative = adminService.getAdminById(adminstrative.getAdminId());
            adminstrative.setAdminImage1(existingAdminstrative.getAdminImage1());
        }

        if (secondaryImageFile != null && !secondaryImageFile.isEmpty()
                && StringUtils.hasText(secondaryImageFile.getOriginalFilename())) {
            adminstrative.setAdminImage2(setimageinDB(secondaryImageFile));
        } else {
            Adminstrative existingAdminstrative = adminService.getAdminById(adminstrative.getAdminId());
            adminstrative.setAdminImage2(existingAdminstrative.getAdminImage2());
        }

        adminService.saveadmin(adminstrative);
        return "redirect:/admin/mainAdmins";
    }

    @GetMapping("/update-internship")
    public String showUpdateInternshipForm(Model model, @RequestParam("internshipId") int internshipId,
            HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        Internship internship = internshipService.getInternshipById(internshipId);
        model.addAttribute("internship", internship);
        return "Admin/UpdatePage/update-internship"; // The HTML template for updating a internship
    }

    @PostMapping("/update-internship")
    public String updateInternship(@ModelAttribute Internship internship,
            @RequestParam("imageFile") MultipartFile imageFile, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";

        if (imageFile != null && !imageFile.isEmpty() && StringUtils.hasText(imageFile.getOriginalFilename())) {
            internship.setImage(setimageinDB(imageFile));
        } else {
            Internship existingInternship = internshipService.getInternshipById(internship.getIntern_id());
            internship.setImage(existingInternship.getImage());
        }

        internshipService.updateInternship(internship);
        return "redirect:/admin/mainInternships";
    }

    @GetMapping("/update-researchmember")
    public String showUpdateResearchMemberForm(Model model, @RequestParam("researchMemberId") int researchMemberId,
            HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        ResearchMember researchMember = researchMemberService.getResearchMemberById(researchMemberId);
        model.addAttribute("researchMember", researchMember);
        return "Admin/UpdatePage/update-researchmember"; // The HTML template for updating a researchMember
    }

    @PostMapping("/update-researchmember")
    public String updateResearchMember(@ModelAttribute ResearchMember researchMember,
            @RequestParam("imageFile") MultipartFile imageFile, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";

        if (imageFile != null && !imageFile.isEmpty() && StringUtils.hasText(imageFile.getOriginalFilename())) {
            researchMember.setResearchMemberImage(setimageinDB(imageFile));
        } else {
            ResearchMember existingResearchMember = researchMemberService
                    .getResearchMemberById(researchMember.getResearchMemberId());
            researchMember.setResearchMemberImage(existingResearchMember.getResearchMemberImage());
        }
        researchMemberService.saveResearchMember(researchMember);
        return "redirect:/admin/mainResearchMembers";
    }

    // DeletePage

    @GetMapping("/delete-collaboration")
    public String deleteCollaboration(@RequestParam("collabId") int collabId, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        collabService.deleteCollabById(collabId);
        return "redirect:/admin/maincollaborations";
    }

    @GetMapping("/delete-competition")
    public String deleteCompetition(@RequestParam("competitionId") int competitionId, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        competitionService.deleteCompetitionById(competitionId);
        return "redirect:/admin/maincompetitions";
    }

    @GetMapping("/delete-grantt")
    public String deleteGrantt(@RequestParam("granttId") int granttId, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        granttService.deleteGranttByGranttId(granttId);
        return "redirect:/admin/maingrants";
    }

    @GetMapping("/delete-industrialReference")
    public String deleteIndustrialReference(@RequestParam("industrialReferenceId") int industrialReferenceId,
            HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        industrialReferenceService.deleteIndustrialReferenceById(industrialReferenceId);
        return "redirect:/admin/mainindustrialreferences";
    }

    @GetMapping("/delete-mou-moa")
    public String deleteMouMoa(@RequestParam("mouMoaId") int mouMoaId, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        mouMoaService.deleteMouMoaById(mouMoaId);
        return "redirect:/admin/mainmoumoas";
    }

    @GetMapping("/delete-news")
    public String deleteNews(@RequestParam("newsId") int newsId, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        newsService.deleteNewsById(newsId);
        return "redirect:/admin/mainnews";
    }

    @GetMapping("/delete-program")
    public String deleteProgram(@RequestParam("programId") int programId, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        programService.deleteProgramById(programId);
        return "redirect:/admin/mainprograms";
    }

    @GetMapping("/delete-publication")
    public String deletePublication(@RequestParam("publicationId") int publicationId, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        publicationService.deletePublicationById(publicationId);
        return "redirect:/admin/mainpublications";
    }

    @GetMapping("/delete-researchpaper")
    public String deleteResearchPaper(@RequestParam("researchPaperId") int researchPaperId, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        researchPaperService.deleteResearchPaperById(researchPaperId);
        return "redirect:/admin/mainresearchpapers";
    }

    @GetMapping("/delete-scientificadvisory")
    public String deleteScientificAdvisory(@RequestParam("scientificAdvisoryId") int scientificAdvisoryId,
            HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        scientificAdvisoryService.deleteScientificAdvisoryById(scientificAdvisoryId);
        return "redirect:/admin/mainscientificadvisory";
    }

    @GetMapping("/delete-visitingfellow")
    public String deleteVisitingFellow(@RequestParam("visitingFellowId") int visitingFellowId, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        visitingFellowService.deleteVisitingFellowById(visitingFellowId);
        return "redirect:/admin/mainvisitingfellows";
    }

    @GetMapping("/delete-academic")
    public String deleteAcademic(@RequestParam("academicId") int academicId, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        academicService.deleteAcademicById(academicId);
        return "redirect:/admin/mainAcademics";
    }

    @GetMapping("/delete-adminstrative")
    public String deleteAdminstrative(@RequestParam("adminId") int adminId, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        adminService.deleteAdminById(adminId);
        return "redirect:/admin/mainAdmins";
    }

    @GetMapping("/delete-internship")
    public String deleteInternship(@RequestParam("internshipId") int internshipId, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        internshipService.deleteInternshipById(internshipId);
        return "redirect:/admin/mainInternships";
    }

    @GetMapping("/delete-researchmember")
    public String deleteResearchMember(@RequestParam("researchMemberId") int researchMemberId, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        researchMemberService.deleteResearchMemberById(researchMemberId);
        return "redirect:/admin/mainResearchMembers";
    }

    // External Functions
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

    @GetMapping("/displayImage")
    @ResponseBody
    public ResponseEntity<byte[]> displayImage(@RequestParam("imageID") int imageID, @RequestParam("type") String type,
            HttpSession session) {
        if (type.equals("competition")) {
            Competition competition = competitionService.getCompetitionById(imageID);
            if (competition != null && competition.getCompetitionImage() != null) {
                System.out.println("Image found");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(competition.getCompetitionImage(), headers, HttpStatus.OK);
            }

        } else if (type.equals("mouMoa")) {
            MouMoa mouMoa = mouMoaService.findByMouMoaId(imageID);
            if (mouMoa != null && mouMoa.getMouMoaImage() != null) {
                System.out.println("Image found");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(mouMoa.getMouMoaImage(), headers, HttpStatus.OK);
            }
        } else if (type.equals("news")) {
            News news = newsService.getNewsById(imageID);
            if (news != null && news.getPrimaryimage() != null) {
                System.out.println("Image found");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(news.getPrimaryimage(), headers, HttpStatus.OK);
            }
        } else if (type.equals("program")) {
            Program program = programService.getProgramById(imageID);
            if (program != null && program.getProgramImage() != null) {
                System.out.println("Image found");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(program.getProgramImage(), headers, HttpStatus.OK);
            }
        } else if (type.equals("research")) {
            ResearchPaper researchPaper = researchPaperService.getResearchPaperById(imageID);
            if (researchPaper != null && researchPaper.getResearchPaperImage() != null) {
                System.out.println("Image found");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(researchPaper.getResearchPaperImage(), headers, HttpStatus.OK);
            }
        } else if (type.equals("visiting")) {
            VisitingFellow visitingFellow = visitingFellowService.findByVisitingFellowId(imageID);
            if (visitingFellow != null && visitingFellow.getVisitingFellowImage() != null) {
                System.out.println("Image found");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(visitingFellow.getVisitingFellowImage(), headers, HttpStatus.OK);
            }
        } else if (type.equals("academic1")) {
            Academic academic = academicService.getAcademicById(imageID);
            if (academic != null && academic.getAcademicImage1() != null) {
                System.out.println("Image found");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(academic.getAcademicImage1(), headers, HttpStatus.OK);
            }
        } else if (type.equals("academic2")) {
            Academic academic = academicService.getAcademicById(imageID);
            if (academic != null && academic.getAcademicImage2() != null) {
                System.out.println("Image found");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(academic.getAcademicImage2(), headers, HttpStatus.OK);
            }
        } else if (type.equals("admin1")) {
            Adminstrative adminstrative = adminService.getAdminById(imageID);
            if (adminstrative != null && adminstrative.getAdminImage1() != null) {
                System.out.println("Image found");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(adminstrative.getAdminImage1(), headers, HttpStatus.OK);
            }
        } else if (type.equals("admin2")) {
            Adminstrative adminstrative = adminService.getAdminById(imageID);
            if (adminstrative != null && adminstrative.getAdminImage2() != null) {
                System.out.println("Image found");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(adminstrative.getAdminImage2(), headers, HttpStatus.OK);
            }
        } else if (type.equals("internship")) {
            Internship internship = internshipService.getInternshipById(imageID);
            if (internship != null && internship.getImage() != null) {
                System.out.println("Image found");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(internship.getImage(), headers, HttpStatus.OK);
            }
        } else if (type.equals("researchMember")) {
            ResearchMember researchMember = researchMemberService.getResearchMemberById(imageID);
            if (researchMember != null && researchMember.getResearchMemberImage() != null) {
                System.out.println("Image found");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(researchMember.getResearchMemberImage(), headers, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
