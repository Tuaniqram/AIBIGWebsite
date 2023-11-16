package com.aibig.umk.model.Directory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

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

}