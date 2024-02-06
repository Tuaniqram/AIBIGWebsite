package com.aibig.umk.model.Directory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "BuletinFile")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BuletinFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buletinFileId")
    private int buletinFileId;

    @Column(name = "buletinFileDate")
    private Date buletinFileDate;

    @Column(name = "buletinTitle")
    private String buletinTitle;

    @Column(name = "buletinIssue")
    private String buletinIssue;

    @Column(name = "buletinPage")
    private int buletinPage;

    @Column(name = "buletinFileType")
    private String buletinFileType;

    @Column(name = "buletinFileName")
    private String buletinFileName;

    @Lob
    @Column(name = "buletinFilePDF", columnDefinition = "MEDIUMBLOB")
    private byte[] buletinFilePDF;

    @Lob
    @Column(name = "buletinFrontPage", columnDefinition = "MEDIUMBLOB")
    private byte[] buletinFrontPage;

    @Transient
    private MultipartFile PDFfile;

    public BuletinFile(BuletinFile buletinFile) {
        this.buletinFileId = buletinFile.getBuletinFileId();
        this.buletinFileDate = buletinFile.getBuletinFileDate();
        this.buletinTitle = buletinFile.getBuletinTitle();
        this.buletinIssue = buletinFile.getBuletinIssue();
        this.buletinPage = buletinFile.getBuletinPage();
        this.buletinFileType = buletinFile.getBuletinFileType();
        this.buletinFileName = buletinFile.getBuletinFileName();
        this.buletinFilePDF = buletinFile.getBuletinFilePDF();
        this.buletinFrontPage = buletinFile.getBuletinFrontPage();
    }

}
