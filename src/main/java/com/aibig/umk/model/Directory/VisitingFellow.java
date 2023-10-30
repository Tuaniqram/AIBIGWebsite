package com.aibig.umk.model.Directory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String visitingFellowStartDate;

    @Column(name = "visitingFellowEndDate")
    private String visitingFellowEndDate;

    @Column(name = "visitingFellowLocation")
    private String visitingFellowLocation;

    @Lob
    @Column(name = "visitingFellowImage", columnDefinition = "MEDIUMBLOB")
    private byte[] visitingFellowImage;

}