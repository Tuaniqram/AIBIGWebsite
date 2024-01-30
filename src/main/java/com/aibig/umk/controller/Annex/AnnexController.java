package com.aibig.umk.controller.Annex;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.aibig.umk.model.Directory.Annex;
import com.aibig.umk.model.Directory.AnnexForm;
import com.aibig.umk.model.Directory.AnnexGallery;
import com.aibig.umk.model.Directory.Publication;
import com.aibig.umk.services.Directory.AnnexFormService;
import com.aibig.umk.services.Directory.AnnexGalleryService;
import com.aibig.umk.services.Directory.AnnexService;

import java.io.IOException;

@Controller
@RequestMapping("/Annex")
public class AnnexController {

    @Autowired
    private AnnexService annexService;

    @Autowired
    private AnnexGalleryService annexGalleryService;

    @Autowired
    private AnnexFormService AnnexFormService;

    @GetMapping("/displayImage")
    @ResponseBody
    public ResponseEntity<byte[]> displayImage(@RequestParam("annexId") int annexId,
            @RequestParam("type") String type) {
        if (type.equals("annex")) {
            Annex annex = annexService.findByAnnexId(annexId);

            if (annex != null && annex.getAnnexImage() != null) {
                System.out.println("Image found");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(annex.getAnnexImage(), headers, HttpStatus.OK);
            }
        } else if (type.equals("annexgallery")) {
            AnnexGallery gallery = annexGalleryService.findByAnnexGalleryId(annexId);
            if (gallery != null && gallery.getAnnexGalleryImage() != null) {
                System.out.println("Image found");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

                return new ResponseEntity<>(gallery.getAnnexGalleryImage(), headers, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/displayPDF")
    @ResponseBody
    public ResponseEntity<byte[]> displayPDF(@RequestParam("pdffile") int annexformId) {
        AnnexForm annexForm = AnnexFormService.getAnnexForm(annexformId);

        if (annexForm != null && annexForm.getAnnexFile() != null) {
            System.out.println("PDF found");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);

            return new ResponseEntity<>(annexForm.getAnnexFile(), headers, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/mainfacilties")
    public String getMainFacilities(Model model) {

        model.addAttribute("titlePage", "FACILITIES AT AIBIG");
        model.addAttribute("breadcumbs1", "Annex");
        model.addAttribute("breadcumbs2", "Main Facilities");

        List<Annex> annexList = annexService.getAllAnnex();
        List<String> type = new ArrayList<>();

        for (Annex annex : annexList) {
            if (!type.contains(annex.getAnnexType())) {
                type.add(annex.getAnnexType());
            }
        }
        model.addAttribute("type", type);

        return "Annex/mainFacilities";
    }

    @GetMapping("/annex-facilties")
    public String getAnnexFacilities(Model model, @RequestParam("type") String type) {
        model.addAttribute("titlePage", type.toUpperCase());
        model.addAttribute("breadcumbs1", "Annex");
        model.addAttribute("breadcumbs2", "Annex Facilities");

        List<Annex> annexList = annexService.getAllAnnex();
        List<Annex> list = new ArrayList<Annex>();
        String annexCat = null;

        for (Annex annex : annexList) {
            if (annex.getAnnexType().equals(type)) {
                list.add(annex);
                annexCat = annex.getAnnexType();
            }
        }
        model.addAttribute("annexCat", annexCat);
        model.addAttribute("annex", list);

        return "Annex/annexFacilities";
    }

    @GetMapping("/mainannexform")
    public String getMainAnnexForm(Model model) {
        model.addAttribute("titlePage", "ANNEX DOWNLOADABLE FORM");
        model.addAttribute("breadcumbs1", "Annex");
        model.addAttribute("breadcumbs2", "Annex Form");

        List<AnnexForm> annexFormList = AnnexFormService.getAllAnnexForm();
        model.addAttribute("annexForm", annexFormList);

        return "Annex/annex-download-form";
    }

}
