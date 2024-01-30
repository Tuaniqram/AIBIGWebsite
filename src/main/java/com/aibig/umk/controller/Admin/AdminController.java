package com.aibig.umk.controller.Admin;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import com.aibig.umk.model.Directory.*;
import com.aibig.umk.model.User.*;
import com.aibig.umk.services.Directory.*;
import com.aibig.umk.services.User.*;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import java.util.ArrayList;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor(force = true)
public class AdminController {

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
        List<Programs> programsList = programService.getAllPrograms();

        model.addAttribute("totalInternship", internshipList.size());
        model.addAttribute("totalPublication", publicationsList.size());
        model.addAttribute("totalGrant", grantsList.size());
        model.addAttribute("totalProgram", programsList.size());

        Academic tempAcademic;
        Adminstrative tempAdminstrative;

        getModelType(model, session);
        return "Admin/Dashboard";
    }

    protected void getModelType(Model model, HttpSession session) {
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

    // Main Page

    @GetMapping("/mainbuletin")
    public String showMainBuletin(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        List<BuletinFile> buletinFiles = buletinFileService.getAllBuletinFiles();
        model.addAttribute("buletinFiles", buletinFiles);
        return "Admin/MainPage/mainbuletin";
    }

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
        List<Programs> programs = programService.getAllPrograms();
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

    @GetMapping("/mainannex")
    public String showMainAnnex(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        List<Annex> annexes = annexService.getAllAnnex();
        List<AnnexForm> annexForms = annexFormService.getAllAnnexForm();
        model.addAttribute("annexes", annexes);
        model.addAttribute("annexForms", annexForms);
        return "Admin/MainPage/mainannex";
    }

    @GetMapping("/mainannexgallery")
    public String showMainAnnexGallery(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        model.addAttribute("breadcrumbs1", "Annex");
        model.addAttribute("breadcrumbs2", "Main Gallery");
        List<AnnexAssociation> associations = annexGalleryService.getAllAnnexAssociation();
        List<AnnexGallery> galleryList = new ArrayList<AnnexGallery>();

        for (AnnexAssociation association : associations) {
            galleryList.add(association.getAnnexGalleryFirst());
        }
        model.addAttribute("galleryList", galleryList);
        return "Admin/MainPage/mainannexgallery";
    }

    @GetMapping("/gallery-folder")
    public String showGalleryFolder(@RequestParam("galleryId") int galleryId, Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/admin";
        model.addAttribute("breadcrumbs1", "Annex");
        model.addAttribute("breadcrumbs2", "Gallery Folder");
        List<AnnexGallery> galleryList = new ArrayList<AnnexGallery>();
        List<AnnexAssociation> galleryAssociations = annexGalleryService.getAllImages();
        AnnexGallery temp = new AnnexGallery();
        for (AnnexAssociation association : galleryAssociations) {
            if (association.getAnnexGalleryFirst().getAnnexGalleryId() == galleryId) {
                galleryList.add(association.getAnnexGallerySecond());
                temp = association.getAnnexGalleryFirst();
            }
        }
        model.addAttribute("galleryList", galleryList);
        model.addAttribute("temp", temp);
        return "Admin/MainPage/gallery-folder";
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

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(competition.getCompetitionImage(), headers, HttpStatus.OK);
            }

        } else if (type.equals("mouMoa")) {
            MouMoa mouMoa = mouMoaService.findByMouMoaId(imageID);
            if (mouMoa != null && mouMoa.getMouMoaImage() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(mouMoa.getMouMoaImage(), headers, HttpStatus.OK);
            }
        } else if (type.equals("news")) {
            News news = newsService.getNewsById(imageID);
            if (news != null && news.getPrimaryimage() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(news.getPrimaryimage(), headers, HttpStatus.OK);
            }
        } else if (type.equals("program")) {
            Programs program = programService.getProgramById(imageID);
            if (program != null && program.getProgramImage() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(program.getProgramImage(), headers, HttpStatus.OK);
            }
        } else if (type.equals("research")) {
            ResearchPaper researchPaper = researchPaperService.getResearchPaperById(imageID);
            if (researchPaper != null && researchPaper.getResearchPaperImage() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(researchPaper.getResearchPaperImage(), headers, HttpStatus.OK);
            }
        } else if (type.equals("visiting")) {
            VisitingFellow visitingFellow = visitingFellowService.findByVisitingFellowId(imageID);
            if (visitingFellow != null && visitingFellow.getVisitingFellowImage() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(visitingFellow.getVisitingFellowImage(), headers, HttpStatus.OK);
            }
        } else if (type.equals("academic1")) {
            Academic academic = academicService.getAcademicById(imageID);
            if (academic != null && academic.getAcademicImage1() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(academic.getAcademicImage1(), headers, HttpStatus.OK);
            }
        } else if (type.equals("academic2")) {
            Academic academic = academicService.getAcademicById(imageID);
            if (academic != null && academic.getAcademicImage2() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(academic.getAcademicImage2(), headers, HttpStatus.OK);
            }
        } else if (type.equals("admin1")) {
            Adminstrative adminstrative = adminService.getAdminById(imageID);
            if (adminstrative != null && adminstrative.getAdminImage1() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(adminstrative.getAdminImage1(), headers, HttpStatus.OK);
            }
        } else if (type.equals("admin2")) {
            Adminstrative adminstrative = adminService.getAdminById(imageID);
            if (adminstrative != null && adminstrative.getAdminImage2() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(adminstrative.getAdminImage2(), headers, HttpStatus.OK);
            }
        } else if (type.equals("internship")) {
            Internship internship = internshipService.getInternshipById(imageID);
            if (internship != null && internship.getImage() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(internship.getImage(), headers, HttpStatus.OK);
            }
        } else if (type.equals("researchMember")) {
            ResearchMember researchMember = researchMemberService.getResearchMemberById(imageID);
            if (researchMember != null && researchMember.getResearchMemberImage() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(researchMember.getResearchMemberImage(), headers, HttpStatus.OK);
            }
        } else if (type.equals("buletin")) {
            BuletinFile buletinFile = buletinFileService.getBuletinFileById(imageID);
            if (buletinFile != null && buletinFile.getBuletinFrontPage() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_PNG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(buletinFile.getBuletinFrontPage(), headers, HttpStatus.OK);
            }
        } else if (type.equals("annex")) {
            Annex annex = annexService.findByAnnexId(imageID);
            if (annex != null && annex.getAnnexImage() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_PNG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(annex.getAnnexImage(), headers, HttpStatus.OK);
            }
        } else if (type.equals("annexGallery")) {
            AnnexGallery annexGallery = annexGalleryService.findByAnnexGalleryId(imageID);
            if (annexGallery != null && annexGallery.getAnnexGalleryImage() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_PNG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(annexGallery.getAnnexGalleryImage(), headers, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // get PDF file from database
    @GetMapping("/displayPDF")
    @ResponseBody
    public ResponseEntity<byte[]> displayPDF(@RequestParam("pdfID") int pdfID, @RequestParam("type") String type,
            HttpSession session) {

        if (type.equals("buletin")) {
            BuletinFile buletinFile = buletinFileService.getBuletinFileById(pdfID);
            if (buletinFile != null && buletinFile.getBuletinFilePDF() != null) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF); // or MediaType.IMAGE_PNG
                ResponseEntity response = new ResponseEntity<>(buletinFile.getBuletinFilePDF(), headers, HttpStatus.OK);

                return new ResponseEntity<>(buletinFile.getBuletinFilePDF(), headers, HttpStatus.OK);
            }
        } else if (type.equals("annexform")) {
            AnnexForm annexForm = annexFormService.getAnnexForm(pdfID);
            if (annexForm != null && annexForm.getAnnexFile() != null) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF); // or MediaType.IMAGE_PNG
                ResponseEntity response = new ResponseEntity<>(annexForm.getAnnexFile(), headers, HttpStatus.OK);

                return new ResponseEntity<>(annexForm.getAnnexFile(), headers, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/displayImagePDF")
    @ResponseBody
    public ResponseEntity<byte[]> displayImagePDF(@RequestParam("imageId") int id,
            HttpSession session) {

        BuletinImage pdfImage = buletinFileService.getBuletinImage(id);
        if (pdfImage != null && pdfImage.getBuletinImage() != null) {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG); // or MediaType.IMAGE_PNG

            return new ResponseEntity<>(pdfImage.getBuletinImage(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
