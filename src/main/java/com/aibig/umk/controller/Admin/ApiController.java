package com.aibig.umk.controller.Admin;

import com.aibig.umk.model.Directory.*;
import com.aibig.umk.model.User.*;
import com.aibig.umk.services.Directory.*;
import com.aibig.umk.services.User.*;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ApiController {

    // Directory Service
    protected final AnnexService annexService;
    protected final AnnexFormService annexFormService;
    protected final AnnexGalleryService annexGalleryService;
    protected final BuletinFileService buletinFileService;
    protected final CollabService collabService;
    protected final CompetitionService competitionService;
    protected final GranttService granttService;
    protected final IndustrialReferenceService industrialReferenceService;
    protected final MouMoaService mouMoaService;
    protected final NewsService newsService;
    protected final ProgramService programService;
    protected final PublicationService publicationService;
    protected final ResearchPaperService researchPaperService;
    protected final ScientificAdvisoryService scientificAdvisoryService;
    protected final VisitingFellowService visitingFellowService;

    // User Service
    protected final AcademicService academicService;
    protected final AdminstrativeService adminService;
    protected final InternshipService internshipService;
    protected final ResearchMemberService researchMemberService;

    @GetMapping("/annex")
    public ResponseEntity<List<Annex>> getAllAnnex() {
        List<Annex> annex = annexService.getAllAnnex();
        return new ResponseEntity<>(annex, HttpStatus.OK);
    }

    @GetMapping("/annexform")
    public ResponseEntity<List<AnnexForm>> getAllAnnexForm() {
        List<AnnexForm> annexForm = annexFormService.getAllAnnexForm();
        return new ResponseEntity<>(annexForm, HttpStatus.OK);
    }

    @GetMapping("/annexgallery")
    public ResponseEntity<List<AnnexGallery>> getAllAnnexGallery() {
        List<AnnexGallery> annexGallery = annexGalleryService.getAllAnnexGallery();
        return new ResponseEntity<>(annexGallery, HttpStatus.OK);
    }

    @GetMapping("/buletinfile")
    public ResponseEntity<List<BuletinFile>> getAllBuletinFile() {
        List<BuletinFile> buletinFile = buletinFileService.getAllBuletinFiles();
        return new ResponseEntity<>(buletinFile, HttpStatus.OK);
    }

    @GetMapping("/collab")
    public ResponseEntity<List<Collaborations>> getAllCollab() {
        List<Collaborations> collab = collabService.getAllCollabs();
        return new ResponseEntity<>(collab, HttpStatus.OK);
    }

    @GetMapping("/competition")
    public ResponseEntity<List<Competition>> getAllCompetition() {
        List<Competition> competition = competitionService.getAllCompetitions();
        return new ResponseEntity<>(competition, HttpStatus.OK);
    }

    @GetMapping("/grantt")
    public ResponseEntity<List<Grantt>> getAllGrantt() {
        List<Grantt> grantt = granttService.getAllGrantts();
        return new ResponseEntity<>(grantt, HttpStatus.OK);
    }

    @GetMapping("/industrialreference")
    public ResponseEntity<List<IndustrialReference>> getAllIndustrialReference() {
        List<IndustrialReference> industrialReference = industrialReferenceService.findAllIndustrialReference();
        return new ResponseEntity<>(industrialReference, HttpStatus.OK);
    }

    @GetMapping("/moumoa")
    public ResponseEntity<List<MouMoa>> getAllMouMoa() {
        List<MouMoa> mouMoa = mouMoaService.findAllMouMoa();
        return new ResponseEntity<>(mouMoa, HttpStatus.OK);
    }

    @GetMapping("/news")
    public ResponseEntity<List<News>> getAllNews() {
        List<News> news = newsService.getAllNews();
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @GetMapping("/program")
    public ResponseEntity<List<Programs>> getAllProgram() {
        List<Programs> program = programService.getAllPrograms();
        return new ResponseEntity<>(program, HttpStatus.OK);
    }

    @GetMapping("/publication")
    public ResponseEntity<List<Publication>> getAllPublication() {
        List<Publication> publication = publicationService.getAllPublication();
        return new ResponseEntity<>(publication, HttpStatus.OK);
    }

    @GetMapping("/researchpapers")
    public ResponseEntity<List<ResearchPaper>> getAllResearchPapers() {
        List<ResearchPaper> researchPapers = researchPaperService.getAllResearchPapers();
        return new ResponseEntity<>(researchPapers, HttpStatus.OK);
    }

    @GetMapping("/scientificadvisory")
    public ResponseEntity<List<ScientificAdvisory>> getAllScientificAdvisory() {
        List<ScientificAdvisory> scientificAdvisory = scientificAdvisoryService.getAllScientificAdvisories();
        return new ResponseEntity<>(scientificAdvisory, HttpStatus.OK);
    }

    @GetMapping("/visitingfellow")
    public ResponseEntity<List<VisitingFellow>> getAllVisitingFellow() {
        List<VisitingFellow> visitingFellow = visitingFellowService.findAllVisitingFellow();
        return new ResponseEntity<>(visitingFellow, HttpStatus.OK);
    }

    @GetMapping("/academic")
    public ResponseEntity<List<Academic>> getAllAcademic() {
        List<Academic> academic = academicService.getAllAcademics();
        return new ResponseEntity<>(academic, HttpStatus.OK);
    }

    @GetMapping("/adminstrative")
    public ResponseEntity<List<Adminstrative>> getAllAdminstrative() {
        List<Adminstrative> adminstrative = adminService.getAllAdmins();
        return new ResponseEntity<>(adminstrative, HttpStatus.OK);
    }

    @GetMapping("/internships")
    public ResponseEntity<List<Internship>> getAllInternships() {
        List<Internship> internships = internshipService.getAllInternships();
        return new ResponseEntity<>(internships, HttpStatus.OK);
    }

    @GetMapping("/researchmembers")
    public ResponseEntity<List<ResearchMember>> getAllResearchMembers() {
        List<ResearchMember> researchMembers = researchMemberService.getAllResearchMembers();
        return new ResponseEntity<>(researchMembers, HttpStatus.OK);
    }

}
