package com.aibig.umk.model.Directory;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "news")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "newsId")
    private int newsId;

    @Column(name = "newsTitle", columnDefinition = "LONGTEXT")
    private String newsTitle;

    @Column(name = "newsDescription", columnDefinition = "LONGTEXT")
    private String newsDescription;

    @Column(name = "newsShortDesc", columnDefinition = "LONGTEXT")
    private String newsShortDesc;

    @Column(name = "newsDate")
    private String newsDate;

    @Column(name = "newsLink")
    private String newsLink;

    @Column(name = "newsCategory")
    private String newsCategory;
    // News, Meeting, seminar, conferences, forums as values

    @Column(name = "newsVideoLink", nullable = true)
    private String newsVideoLink;

    @Lob
    @Column(name = "primaryimage", columnDefinition = "MEDIUMBLOB")
    private byte[] primaryimage;

    // 4 images
    @Lob
    @Column(name = "image1", columnDefinition = "MEDIUMBLOB", nullable = true)
    private byte[] image1;

    @Lob
    @Column(name = "image2", columnDefinition = "MEDIUMBLOB", nullable = true)
    private byte[] image2;

    @Lob
    @Column(name = "image3", columnDefinition = "MEDIUMBLOB", nullable = true)
    private byte[] image3;

    @Lob
    @Column(name = "image4", columnDefinition = "MEDIUMBLOB", nullable = true)
    private byte[] image4;

    @Transient
    private MultipartFile imageFile;

}
