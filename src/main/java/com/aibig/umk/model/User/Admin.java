package com.aibig.umk.model.User;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminId")
    private int adminId;

    @Column(name = "adminUsername")
    private String adminUsername;

    @Column(name = "adminPassword")
    private String adminPassword;

    @Column(name = "adminName")
    private String adminName;

    @Column(name = "adminRole")
    private String adminRole;

    @Column(name = "adminDepartment")
    private String adminDepartment;

    @Column(name = "adminNoPhone")
    private String adminNoPhone;

    @Column(name = "adminEmail")
    private String adminEmail;

    @Lob
    @Column(name = "adminImage1", columnDefinition = "MEDIUMBLOB")
    private byte[] adminImage1;

    @Column(name = "adminImage2", columnDefinition = "MEDIUMBLOB")
    private byte[] adminImage2;

    @Transient
    private MultipartFile imageFile;

    public Admin() {
    }

    public Admin(int adminId, String adminUsername, String adminPassword,
            String adminName, String adminRole, String adminDepartment,
            String adminNoPhone, String adminEmail, byte[] adminImage1, byte[] adminImage2,
            MultipartFile imageFile) {
        this.adminId = adminId;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.adminName = adminName;
        this.adminRole = adminRole;
        this.adminDepartment = adminDepartment;
        this.adminNoPhone = adminNoPhone;
        this.adminEmail = adminEmail;
        this.adminImage1 = adminImage1;
        this.adminImage2 = adminImage2;
        this.imageFile = imageFile;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }

    public String getAdminDepartment() {
        return adminDepartment;
    }

    public void setAdminDepartment(String adminDepartment) {
        this.adminDepartment = adminDepartment;
    }

    public String getAdminNoPhone() {
        return adminNoPhone;
    }

    public void setAdminNoPhone(String adminNoPhone) {
        this.adminNoPhone = adminNoPhone;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public byte[] getAdminImage1() {
        return adminImage1;
    }

    public void setAdminImage1(byte[] adminImage1) {
        this.adminImage1 = adminImage1;
    }

    public byte[] getAdminImage2() {
        return adminImage2;
    }

    public void setAdminImage2(byte[] adminImage2) {
        this.adminImage2 = adminImage2;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

}
