package com.aibig.umk.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@Controller
@RequestMapping("/delete")
public class DeleteAdmin extends AdminController {

    public DeleteAdmin(AnnexService annexService, AnnexFormService annexFormService,
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
}
