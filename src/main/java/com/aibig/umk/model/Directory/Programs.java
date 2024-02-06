package com.aibig.umk.model.Directory;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "program")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Programs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "programId")
    private int programId;

    @Column(name = "programShortName")
    private String programShortName;

    @Column(name = "programName")
    private String programName;

    @Column(name = "programDescription", columnDefinition = "LONGTEXT")
    private String programDescription;

    @Column(name = "programVenue", columnDefinition = "LONGTEXT")
    private String programVenue;

    @Column(name = "programDate")
    private String programDate;

    @Column(name = "programTime")
    private String programTime;

    @Column(name = "programFee")
    private String programFee;

    @Column(name = "programForm")
    private String programForm;

    @Lob
    @Column(name = "programImage", columnDefinition = "MEDIUMBLOB")
    private byte[] programImage;

    @Transient
    private MultipartFile imageFile;

    public Programs(Programs program) {
        this.programId = program.getProgramId();
        this.programShortName = program.getProgramShortName();
        this.programName = program.getProgramName();
        this.programDescription = program.getProgramDescription();
        this.programVenue = program.getProgramVenue();
        this.programDate = program.getProgramDate();
        this.programTime = program.getProgramTime();
        this.programFee = program.getProgramFee();
        this.programForm = program.getProgramForm();
        this.programImage = program.getProgramImage();
    }

}
