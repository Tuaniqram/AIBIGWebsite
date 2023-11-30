package com.aibig.umk.model.Directory;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

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

    @Column(name = "newsDescription", columnDefinition = "LONGTEXT COLLATE utf8_unicode_ci NOT NULL")
    private String newsDescription;

    @Column(name = "newsShortDesc", columnDefinition = "LONGTEXT")
    private String newsShortDesc;

    @Column(name = "newsDate", columnDefinition = "DATE")
    private Date newsDate;

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

    @Transient
    private MultipartFile imageFile;

}
