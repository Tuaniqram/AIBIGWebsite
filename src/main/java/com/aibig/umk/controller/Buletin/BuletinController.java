package com.aibig.umk.controller.Buletin;

import com.aibig.umk.model.Directory.BuletinFile;
import com.aibig.umk.model.Directory.BuletinImage;
import com.aibig.umk.services.Directory.BuletinFileService;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/buletin")
public class BuletinController {

    @Autowired
    private BuletinFileService buletinFileService;

    @GetMapping("/displayimagePDF")
    @ResponseBody
    public ResponseEntity<byte[]> displayImagePDF(@RequestParam("pdfid") int pdfid) throws IOException {
        BuletinFile buletinFile = buletinFileService.getBuletinFileById(pdfid);
        if (buletinFile != null && buletinFile.getBuletinFrontPage() != null) {
            System.out.println("Image found");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

            return new ResponseEntity<>(buletinFile.getBuletinFrontPage(), headers, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all-issues")
    public String getAllBuletinFiles(Model model) {
        model.addAttribute("titlePage", "AI SPECTRUM");
        model.addAttribute("breadcumbs1", "Buletin");
        model.addAttribute("breadcumbs2", "All Buletin");
        List<BuletinFile> buletinFiles = buletinFileService.getAllBuletinFiles();
        model.addAttribute("buletinFiles", buletinFiles);
        System.out.println(buletinFiles.size());
        return "Buletin/allbuletinissue";
    }

    @GetMapping("/issue")
    public String getBuletinFileById(@RequestParam("pdfid") int id, Model model)
            throws IOException {
        BuletinFile buletinFile = buletinFileService.getBuletinFileById(id);
        model.addAttribute("titlePage", buletinFile.getBuletinTitle().toUpperCase());
        model.addAttribute("breadcumbs1", "Buletin");
        model.addAttribute("breadcumbs2", buletinFile.getBuletinIssue());
        model.addAttribute("buletinFile", buletinFile);

        List<BuletinImage> images = new ArrayList<>();
        for (int page = 0; page < buletinFile.getBuletinPage(); page++) {
            BuletinImage buletinImage = buletinFileService.getBuletinImages(id, page + 1);
            images.add(buletinImage);
        }
        model.addAttribute("images", images);
        return "Buletin/buletin-issue";
    }

}
