package com.aibig.umk.model.Directory;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "researchpaper")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResearchPaper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "researchPaperId")
    private int researchPaperId;

    @Column(name = "researchPaperTitle")
    private String researchPaperTitle;

    @Column(name = "researchPaperAuthor")
    private String researchPaperAuthor;

    @Column(name = "researchPaperDate")
    private String researchPaperDate;

    @Column(name = "researchPaperDescription", columnDefinition = "LONGTEXT")
    private String researchPaperDescription;

    @Column(name = "researchPaperLink")
    private String researchPaperLink;

    @Lob
    @Column(name = "researchPaperImage", columnDefinition = "MEDIUMBLOB")
    private byte[] researchPaperImage;

    @Transient
    private MultipartFile imageFile;

}
