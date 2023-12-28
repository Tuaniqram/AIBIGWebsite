package com.aibig.umk.controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aibig.umk.model.Directory.Collaborations;
import com.aibig.umk.model.Directory.Competition;
import com.aibig.umk.model.Directory.Grantt;
import com.aibig.umk.model.Directory.IndustrialReference;
import com.aibig.umk.model.Directory.MouMoa;
import com.aibig.umk.model.Directory.News;
import com.aibig.umk.model.Directory.Program;
import com.aibig.umk.model.Directory.Publication;
import com.aibig.umk.model.Directory.ResearchPaper;
import com.aibig.umk.model.Directory.ScientificAdvisory;
import com.aibig.umk.model.Directory.VisitingFellow;
import com.aibig.umk.model.User.Academic;
import com.aibig.umk.model.User.Adminstrative;
import com.aibig.umk.model.User.Internship;
import com.aibig.umk.model.User.ResearchMember;
import com.aibig.umk.services.Directory.AnnexFormService;
import com.aibig.umk.services.Directory.AnnexGalleryService;
import com.aibig.umk.services.Directory.AnnexService;
import com.aibig.umk.services.Directory.BuletinFileService;
import com.aibig.umk.services.Directory.CollabService;
import com.aibig.umk.services.Directory.CompetitionService;
import com.aibig.umk.services.Directory.GranttService;
import com.aibig.umk.services.Directory.IndustrialReferenceService;
import com.aibig.umk.services.Directory.MouMoaService;
import com.aibig.umk.services.Directory.NewsService;
import com.aibig.umk.services.Directory.ProgramService;
import com.aibig.umk.services.Directory.PublicationService;
import com.aibig.umk.services.Directory.ResearchPaperService;
import com.aibig.umk.services.Directory.ScientificAdvisoryService;
import com.aibig.umk.services.Directory.VisitingFellowService;
import com.aibig.umk.services.User.AcademicService;
import com.aibig.umk.services.User.AdminstrativeService;
import com.aibig.umk.services.User.InternshipService;
import com.aibig.umk.services.User.ResearchMemberService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@Controller
@RequestMapping("/update")
public class UpdateAdmin extends AdminController {

    public UpdateAdmin(AnnexService annexService, AnnexFormService annexFormService,
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

}
