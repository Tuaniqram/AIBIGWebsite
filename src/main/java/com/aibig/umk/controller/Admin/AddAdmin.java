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
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@Controller
@RequestMapping("/add")
public class AddAdmin extends AdminController {

    public AddAdmin(AnnexService annexService, AnnexFormService annexFormService,
            AnnexGalleryService annexGalleryService, BuletinFileService buletinFileService, CollabService collabService,
            CompetitionService competitionService, GranttService granttService,
            IndustrialReferenceService industrialReferenceService, MouMoaService mouMoaService,
            NewsService newsService, ProgramService programService, PublicationService publicationService,
            ResearchPaperService researchPaperService, ScientificAdvisoryService scientificAdvisoryService,
            VisitingFellowService visitingFellowService, AcademicService academicService,
            AdminstrativeService adminService, InternshipService internshipService,
            ResearchMemberService researchMemberService) {
        super(annexService, annexFormService, annexGalleryService, buletinFileService, collabService,
                competitionService, granttService,
                industrialReferenceService, mouMoaService, newsService, programService, publicationService,
                researchPaperService, scientificAdvisoryService, visitingFellowService, academicService, adminService,
                internshipService, researchMemberService);
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
        internship.setImage(super.setimageinDB(imageFile));

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
        super.collabService.saveCollab(collab);
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

    @GetMapping("/add-buletin")
    public String showAddBuletinForm(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        model.addAttribute("buletin", new BuletinFile());
        return "Admin/AddingNewData/add-buletin"; // The HTML template for adding a buletin
    }

    @PostMapping("/add-buletin")
    public String addBuletin(@ModelAttribute BuletinFile buletinfile, @RequestParam("pdfFile") MultipartFile pdfFile,
            HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";

        try {
            buletinFileService.saveBuletinFile(buletinfile, pdfFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/mainbuletin";
    }
}
