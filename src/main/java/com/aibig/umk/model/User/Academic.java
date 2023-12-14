package com.aibig.umk.model.User;

import org.springframework.web.multipart.MultipartFile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;;

@Entity
@Table(name = "academic")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    public Academic(Academic academic) {
        this.academicId = academic.getAcademicId();
        this.academicUsername = academic.getAcademicUsername();
        this.academicPassword = academic.getAcademicPassword();
        this.academicName = academic.getAcademicName();
        this.academicRole = academic.getAcademicRole();
        this.academicDepartment = academic.getAcademicDepartment();
        this.academicNoPhone = academic.getAcademicNoPhone();
        this.academicEmail = academic.getAcademicEmail();
        this.academicImage1 = academic.getAcademicImage1();
        this.academicImage2 = academic.getAcademicImage2();
    }

}
