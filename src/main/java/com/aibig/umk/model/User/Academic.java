package com.aibig.umk.model.User;

import org.springframework.web.multipart.MultipartFile;
import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "academic")
public class Academic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "academicId")
    private int academicId;

    @Column(name = "academicUsername")
    private String academicUsername;

    @Column(name = "academicPassword")
    private String academicPassword;

    @Column(name = "academicName")
    private String academicName;

    @Column(name = "academicRole")
    private String academicRole;

    @Column(name = "academicDepartment")
    private String academicDepartment;

    @Column(name = "academicNoPhone")
    private String academicNoPhone;

    @Column(name = "academicEmail")
    private String academicEmail;

    @Lob
    @Column(name = "academicImage1", columnDefinition = "MEDIUMBLOB")
    private byte[] academicImage1;

    @Lob
    @Column(name = "academicImage2", columnDefinition = "MEDIUMBLOB")
    private byte[] academicImage2;

    @Transient
    private MultipartFile imageFile;

    public Academic() {
    }

    public Academic(int academicId, String academicUsername, String academicPassword,
            String academicName, String academicRole, String academicDepartment,
            String academicNoPhone, String academicEmail, byte[] academicImage1, byte[] academicImage2,
            MultipartFile imageFile) {
        this.academicId = academicId;
        this.academicUsername = academicUsername;
        this.academicPassword = academicPassword;
        this.academicName = academicName;
        this.academicRole = academicRole;
        this.academicDepartment = academicDepartment;
        this.academicNoPhone = academicNoPhone;
        this.academicEmail = academicEmail;
        this.academicImage1 = academicImage1;
        this.academicImage2 = academicImage2;
        this.imageFile = imageFile;
    }

    public int getacademicId() {
        return academicId;
    }

    public void setacademicId(int academicId) {
        this.academicId = academicId;
    }

    public String getacademicUsername() {
        return academicUsername;
    }

    public void setacademicUsername(String academicUsername) {
        this.academicUsername = academicUsername;
    }

    public String getacademicPassword() {
        return academicPassword;
    }

    public void setacademicPassword(String academicPassword) {
        this.academicPassword = academicPassword;
    }

    public String getacademicName() {
        return academicName;
    }

    public void setacademicName(String academicName) {
        this.academicName = academicName;
    }

    public String getacademicRole() {
        return academicRole;
    }

    public void setacademicRole(String academicRole) {
        this.academicRole = academicRole;
    }

    public String getacademicDepartment() {
        return academicDepartment;
    }

    public void setacademicDepartment(String academicDepartment) {
        this.academicDepartment = academicDepartment;
    }

    public String getacademicNoPhone() {
        return academicNoPhone;
    }

    public void setacademicNoPhone(String academicNoPhone) {
        this.academicNoPhone = academicNoPhone;
    }

    public String getacademicEmail() {
        return academicEmail;
    }

    public void setacademicEmail(String academicEmail) {
        this.academicEmail = academicEmail;
    }

    public byte[] getacademicImage1() {
        return academicImage1;
    }

    public void setacademicImage1(byte[] academicImage1) {
        this.academicImage1 = academicImage1;
    }

    public byte[] getacademicImage2() {
        return academicImage2;
    }

    public void setacademicImage2(byte[] academicImage2) {
        this.academicImage2 = academicImage2;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

}
