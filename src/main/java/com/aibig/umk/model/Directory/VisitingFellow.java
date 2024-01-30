package com.aibig.umk.model.Directory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "visitingFellow")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VisitingFellow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visitingFellowId")
    private int visitingFellowId;

    @Column(name = "visitingFellowName")
    private String visitingFellowName;

    @Column(name = "visitingFellowRole")
    private String visitingFellowRole;

    @Column(name = "visitingFellowStartDate")
    private Date visitingFellowStartDate;

    @Column(name = "visitingFellowEndDate")
    private Date visitingFellowEndDate;

    @Column(name = "visitingFellowLocation")
    private String visitingFellowLocation;

    @Lob
    @Column(name = "visitingFellowImage", columnDefinition = "MEDIUMBLOB")
    private byte[] visitingFellowImage;

    @Transient
    private MultipartFile imageFile;

    public VisitingFellow(VisitingFellow visitingFellow) {
        this.visitingFellowId = visitingFellow.getVisitingFellowId();
        this.visitingFellowName = visitingFellow.getVisitingFellowName();
        this.visitingFellowRole = visitingFellow.getVisitingFellowRole();
        this.visitingFellowStartDate = visitingFellow.getVisitingFellowStartDate();
        this.visitingFellowEndDate = visitingFellow.getVisitingFellowEndDate();
        this.visitingFellowLocation = visitingFellow.getVisitingFellowLocation();
        this.visitingFellowImage = visitingFellow.getVisitingFellowImage();
    }

}