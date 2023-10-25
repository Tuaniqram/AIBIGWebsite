package com.aibig.umk.model.Directory;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;

@Entity
@Table(name = "program")
public class Program {
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

    public Program() {
    }

    public Program(int programId, String programShortName, String programName, String programDescription,
            String programVenue, String programDate, String programTime, String programFee, String programForm,
            byte[] programImage, MultipartFile imageFile) {
        this.programId = programId;
        this.programShortName = programShortName;
        this.programName = programName;
        this.programDescription = programDescription;
        this.programVenue = programVenue;
        this.programDate = programDate;
        this.programTime = programTime;
        this.programFee = programFee;
        this.programForm = programForm;
        this.programImage = programImage;
        this.imageFile = imageFile;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramShortName() {
        return programShortName;
    }

    public void setProgramShortName(String programShortName) {
        this.programShortName = programShortName;
    }

    public String getProgramDescription() {
        return programDescription;
    }

    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    public String getProgramVenue() {
        return programVenue;
    }

    public void setProgramVenue(String programVenue) {
        this.programVenue = programVenue;
    }

    public String getProgramDate() {
        return programDate;
    }

    public void setProgramDate(String programDate) {
        this.programDate = programDate;
    }

    public String getProgramTime() {
        return programTime;
    }

    public void setProgramTime(String programTime) {
        this.programTime = programTime;
    }

    public String getProgramFee() {
        return programFee;
    }

    public void setProgramFee(String programFee) {
        this.programFee = programFee;
    }

    public String getProgramForm() {
        return programForm;
    }

    public void setProgramForm(String programForm) {
        this.programForm = programForm;
    }

    public byte[] getProgramImage() {
        return programImage;
    }

    public void setProgramImage(byte[] programImage) {
        this.programImage = programImage;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public Program orElse(Object object) {
        return null;
    }

}
