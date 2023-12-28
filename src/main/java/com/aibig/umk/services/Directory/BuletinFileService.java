package com.aibig.umk.services.Directory;

import com.aibig.umk.model.Directory.BuletinFile;
import com.aibig.umk.model.Directory.BuletinImage;
import com.aibig.umk.repository.Directory.BuletinFileRepository;
import com.aibig.umk.repository.Directory.BuletinImageRepository;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

@Service
public class BuletinFileService {

    @Autowired
    private BuletinFileRepository buletinFileRepository;

    @Autowired
    private BuletinImageRepository buletinImageRepository;

    public List<BuletinFile> getAllBuletinFiles() {
        return buletinFileRepository.findAll();
    }

    public BuletinFile getBuletinFileById(int id) {
        return buletinFileRepository.findById(id).orElse(null);
    }

    public void saveBuletinFile(BuletinFile buletinFile, MultipartFile pdfFile) throws IOException {
        String fileName = StringUtils.cleanPath(pdfFile.getOriginalFilename());
        if (fileName.endsWith("..")) {
            throw new RuntimeException("File name contains invalid path sequence " + fileName);
        }
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] documentBytes = pdfFile.getBytes();
        PDDocument document = PDDocument.load(documentBytes);
        if (!document.isEncrypted()) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            buletinFile.setBuletinPage(document.getPages().getCount());
            buletinFile.setBuletinFileName(fileName);
            buletinFile.setBuletinFileType(fileType);
            buletinFile.setBuletinFilePDF(pdfFile.getBytes());
            BufferedImage image = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);
            buletinFile.setBuletinFrontPage(convertImageToByteArray(image, "PNG"));
            buletinFileRepository.save(buletinFile);

            saveBuletinImage(buletinFile);

            document.close();
        }
    }

    public void deleteBuletinFile(int id) {
        buletinFileRepository.deleteById(id);
    }

    public void updateBuletinFile(BuletinFile buletinFile) {
        BuletinFile existingBuletinFile = new BuletinFile(buletinFile);
        buletinFileRepository.save(existingBuletinFile);
    }

    public byte[] convertImageToByteArray(BufferedImage image, String format) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, format, baos);
        return baos.toByteArray();
    }

    public BuletinImage getBuletinImages(int buletinFileId, int buletinPage) {
        // BuletinFile existingBuletinFile =
        // buletinFileRepository.findById(buletinFileId).orElse(null);
        return buletinImageRepository.findByBuletinFileIdAndBuletinPage(buletinFileId, buletinPage);
    }

    public BuletinImage getBuletinImage(int id) {
        return buletinImageRepository.findById(id).orElse(null);
    }

    public void saveBuletinImage(BuletinFile buletinFile) throws IOException {

        byte[] documentBytes = buletinFile.getBuletinFilePDF();
        PDDocument document = PDDocument.load(documentBytes);

        if (!document.isEncrypted()) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            for (int i = 0; i < document.getPages().getCount(); i++) {
                BuletinImage buletinImage = new BuletinImage();
                buletinImage.setBuletinFileId(buletinFile);
                buletinImage.setBuletinPage(i + 1);
                BufferedImage image = pdfRenderer.renderImageWithDPI(i, 300, ImageType.RGB);
                buletinImage.setBuletinImage(convertImageToByteArray(image, "PNG"));
                buletinImageRepository.save(buletinImage);
            }
            document.close();
        }

    }
}
