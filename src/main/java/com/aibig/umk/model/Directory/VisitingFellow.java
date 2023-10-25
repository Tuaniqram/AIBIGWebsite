package com.aibig.umk.model.Directory;

import jakarta.persistence.*;

@Entity
@Table(name = "visitingFellow")
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

    public VisitingFellow() {
    }

    public VisitingFellow(int visitingFellowId, String visitingFellowName, String visitingFellowRole,
            String visitingFellowStartDate, String visitingFellowEndDate, String visitingFellowLocation) {
        this.visitingFellowId = visitingFellowId;
        this.visitingFellowName = visitingFellowName;
        this.visitingFellowRole = visitingFellowRole;
        this.visitingFellowStartDate = visitingFellowStartDate;
        this.visitingFellowEndDate = visitingFellowEndDate;
        this.visitingFellowLocation = visitingFellowLocation;
    }

    public int getVisitingFellowId() {
        return visitingFellowId;
    }

    public void setVisitingFellowId(int visitingFellowId) {
        this.visitingFellowId = visitingFellowId;
    }

    public String getVisitingFellowName() {
        return visitingFellowName;
    }

    public void setVisitingFellowName(String visitingFellowName) {
        this.visitingFellowName = visitingFellowName;
    }

    public String getVisitingFellowRole() {
        return visitingFellowRole;
    }

    public void setVisitingFellowRole(String visitingFellowRole) {
        this.visitingFellowRole = visitingFellowRole;
    }

    public String getVisitingFellowStartDate() {
        return visitingFellowStartDate;
    }

    public void setVisitingFellowStartDate(String visitingFellowStartDate) {
        this.visitingFellowStartDate = visitingFellowStartDate;
    }

    public String getVisitingFellowEndDate() {
        return visitingFellowEndDate;
    }

    public void setVisitingFellowEndDate(String visitingFellowEndDate) {
        this.visitingFellowEndDate = visitingFellowEndDate;
    }

    public String getVisitingFellowLocation() {
        return visitingFellowLocation;
    }

    public void setVisitingFellowLocation(String visitingFellowLocation) {
        this.visitingFellowLocation = visitingFellowLocation;
    }

}