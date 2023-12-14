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

    public News(News news) {
        this.newsId = news.getNewsId();
        this.newsTitle = news.getNewsTitle();
        this.newsDescription = news.getNewsDescription();
        this.newsShortDesc = news.getNewsShortDesc();
        this.newsDate = news.getNewsDate();
        this.newsLink = news.getNewsLink();
        this.newsCategory = news.getNewsCategory();
        this.newsVideoLink = news.getNewsVideoLink();
        this.primaryimage = news.getPrimaryimage();
    }

}
