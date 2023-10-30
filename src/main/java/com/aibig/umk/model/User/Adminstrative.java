package com.aibig.umk.model.User;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admin")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Adminstrative {
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

}
