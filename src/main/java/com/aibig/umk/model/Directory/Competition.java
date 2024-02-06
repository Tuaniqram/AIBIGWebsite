package com.aibig.umk.model.Directory;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Competition")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competitionId")
    private int competitionId;

    @Column(name = "competitionName")
    private String competitionName;

    @Column(name = "competitionShortName")
    private String competitionShortName;

    @Column(name = "competitionDescription", columnDefinition = "LONGTEXT COLLATE utf8_unicode_ci NOT NULL")
    private String competitionDescription;

    @Column(name = "competitionDate")
    private String competitionDate;

    @Column(name = "competitionLink")
    private String competitionLink;

    @Lob
    @Column(name = "competitionImage", columnDefinition = "MEDIUMBLOB")
    private byte[] competitionImage;

    @Transient
    private MultipartFile imageFile;

    public Competition(Competition competition) {
        this.competitionId = competition.getCompetitionId();
        this.competitionName = competition.getCompetitionName();
        this.competitionShortName = competition.getCompetitionShortName();
        this.competitionDescription = competition.getCompetitionDescription();
        this.competitionDate = competition.getCompetitionDate();
        this.competitionLink = competition.getCompetitionLink();
        this.competitionImage = competition.getCompetitionImage();
    }
}