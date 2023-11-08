package com.aibig.umk.controller.RnD;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.aibig.umk.model.Directory.Grantt;
import com.aibig.umk.model.Directory.Publication;
import com.aibig.umk.services.Directory.GranttService;
import com.aibig.umk.services.Directory.PublicationService;

@RequestMapping("/rnd")
@Controller
public class RnDController {

    private final GranttService granttService;
    private final PublicationService publicationService;

    @Autowired
    public RnDController(GranttService granttService, PublicationService publicationService) {
        this.granttService = granttService;
        this.publicationService = publicationService;
    }

    @GetMapping("/researchataibig")
    public String showResearchAtAIBig(Model model) {
        model.addAttribute("breadcumbs1", "RnD");
        model.addAttribute("breadcumbs2", "RESEARCH @ AIBIG");
        return "RnD/ResearchAtAibig";
    }

    @GetMapping("/covid19")
    public String showCovid19(Model model) {
        model.addAttribute("breadcumbs1", "RnD");
        model.addAttribute("breadcumbs2", "COVID-19");
        return "RnD/Covid19";
    }

    @GetMapping("/grantt")
    public String showGrantt(Model model) {
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
        model.addAttribute("breadcumbs1", "RnD");
        model.addAttribute("breadcumbs2", "Publication");

        List<Publication> publicationList = publicationService.getAllPublication();
        List<String> categories = new ArrayList<>();

        for (Publication publication : publicationList) {
            if (!categories.contains(publication.getPublicationCategory())) {
                categories.add(publication.getPublicationCategory());
            }
        }
        model.addAttribute("categories", categories);
        model.addAttribute("publications", publicationList);

        return "RnD/Publication";
    }

    @GetMapping("/research-collaboration")
    public String showResearchCollaboration(Model model) {
        model.addAttribute("breadcumbs1", "RnD");
        model.addAttribute("breadcumbs2", "Research Collaboration");
        return "RnD/research-Collaborration";
    }
}
