package com.aibig.umk.controller.RnD;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.aibig.umk.model.Directory.Collaborations;
import com.aibig.umk.model.Directory.Grantt;
import com.aibig.umk.model.Directory.MouMoa;
import com.aibig.umk.model.Directory.Publication;
import com.aibig.umk.model.Directory.ResearchPaper;
import com.aibig.umk.services.Directory.CollabService;
import com.aibig.umk.services.Directory.GranttService;
import com.aibig.umk.services.Directory.MouMoaService;
import com.aibig.umk.services.Directory.PublicationService;
import com.aibig.umk.services.Directory.ResearchPaperService;

import lombok.AllArgsConstructor;

@RequestMapping("/rnd")
@Controller
@AllArgsConstructor
public class RnDController {

    private final GranttService granttService;
    private final PublicationService publicationService;
    private final ResearchPaperService researchPaperService;
    private final CollabService collabService;
    private final MouMoaService mouMoaService;

    @GetMapping("/displayImage")
    @ResponseBody
    public ResponseEntity<byte[]> displayImage(@RequestParam("imageID") int imageID,
            @RequestParam("type") String type) {
        if (type.equals("research")) {
            ResearchPaper rPapper = researchPaperService.getResearchPaperById(imageID);
            if (rPapper != null && rPapper.getResearchPaperImage() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(rPapper.getResearchPaperImage(), headers, HttpStatus.OK);
            }
        } else if (type.equals("moumoa")) {
            MouMoa mouMoa = mouMoaService.findByMouMoaId(imageID);
            if (mouMoa != null && mouMoa.getMouMoaImage() != null) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(mouMoa.getMouMoaImage(), headers, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/researchataibig")
    public String showResearchAtAIBig(Model model) {
        model.addAttribute("titlePage", "RESEARCH @ AIBIG");
        model.addAttribute("breadcumbs1", "RnD");
        model.addAttribute("breadcumbs2", "RESEARCH @ AIBIG");
        List<ResearchPaper> ArtificialIntelligence = researchPaperService
                .getAllResearchPapersByResearchPaperCategory("Artificial Intelligence");
        List<ResearchPaper> BigData = researchPaperService.getAllResearchPapersByResearchPaperCategory("Big Data");
        List<ResearchPaper> MachineLearning = researchPaperService
                .getAllResearchPapersByResearchPaperCategory("Machince Learning");
        List<ResearchPaper> InternetOfThings = researchPaperService
                .getAllResearchPapersByResearchPaperCategory("Internet of Things");
        List<ResearchPaper> DataScience = researchPaperService
                .getAllResearchPapersByResearchPaperCategory("Data Science");

        model.addAttribute("ArtificialIntelligence", ArtificialIntelligence);
        model.addAttribute("BigData", BigData);
        model.addAttribute("MachineLearning", MachineLearning);
        model.addAttribute("InternetOfThings", InternetOfThings);
        model.addAttribute("DataScience", DataScience);
        return "RnD/ResearchAtAibig";
    }

    @GetMapping("/covid19")
    public String showCovid19(Model model) {
        model.addAttribute("titlePage", "COVID-19 PROJECTS");
        model.addAttribute("breadcumbs1", "RnD");
        model.addAttribute("breadcumbs2", "COVID-19");
        return "RnD/Covid19";
    }

    @GetMapping("/grantt")
    public String showGrantt(Model model) {
        model.addAttribute("titlePage", "RESEARCH GRANT");
        model.addAttribute("breadcumbs1", "RnD");
        model.addAttribute("breadcumbs2", "GRANTT");

        List<Grantt> grantts = granttService.getAllGrantts();
        List<Grantt> PrincipalInvestigator = new ArrayList<>();
        List<Grantt> ProjectMember = new ArrayList<>();

        for (Grantt grantt : grantts) {
            if (grantt.getGranttCategory().equals("Principal Investigator")) {
                PrincipalInvestigator.add(grantt);
            }
            if (grantt.getGranttCategory().equals("Project Member")) {
                ProjectMember.add(grantt);
            }
        }
        model.addAttribute("granttCat1", PrincipalInvestigator);
        model.addAttribute("granttCat2", ProjectMember);
        return "RnD/Grantt";
    }

    @GetMapping("/publication")
    public String showPublication(Model model) {
        model.addAttribute("titlePage", "OUR PUBLICATION");
        model.addAttribute("breadcumbs1", "RnD");
        model.addAttribute("breadcumbs2", "Publication");

        List<Publication> publicationList = publicationService.getAllPublication();
        List<String> categories = new ArrayList<>();

        for (Publication publication : publicationList) {
            if (!categories.contains(publication.getPublicationCategory())) {
                categories.add(publication.getPublicationCategory());
                System.out.println(publication.getPublicationCategory());
            }
        }

        // Sort the publicationList by category
        publicationList.sort(Comparator.comparing(Publication::getPublicationCategory));

        model.addAttribute("categories", categories);
        model.addAttribute("publications", publicationList);

        return "RnD/Publication";
    }

    @GetMapping("/research-collaboration")
    public String showResearchCollaboration(Model model) {
        model.addAttribute("titlePage", "RESEARCH COLLABORATION");
        model.addAttribute("breadcumbs1", "RnD");
        model.addAttribute("breadcumbs2", "Research Collaboration");
        List<Collaborations> collabs = collabService.getAllCollabs();
        model.addAttribute("collabs", collabs);
        return "RnD/research-Collaborration";
    }

    @GetMapping("/mou-moa")
    public String showMouMoa(Model model) {
        model.addAttribute("titlePage", "MOU/MOA");
        model.addAttribute("breadcumbs1", "RnD");
        model.addAttribute("breadcumbs2", "MOU/MOA");

        // get a latest data from database
        MouMoa latest = mouMoaService.getLatestMouMoa();
        model.addAttribute("latest", latest);

        return "RnD/MoUMoA";
    }
}
