package com.aibig.umk.model.Directory;

import jakarta.persistence.*;

@Entity
@Table(name = "industrialReference")
public class IndustrialReference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "industrialReferenceId")
    private int industrialReferenceId;

    @Column(name = "industrialReferenceName")
    private String industrialReferenceName;

    @Column(name = "industrialReferenceDesignation", columnDefinition = "LONGTEXT")
    private String industrialReferenceDesignation;

    @Column(name = "industrialReferenceCompany", columnDefinition = "LONGTEXT")
    private String industrialReferenceCompany;

    public IndustrialReference() {
    }

    public IndustrialReference(int industrialReferenceId, String industrialReferenceName,
            String industrialReferenceDesignation, String industrialReferenceCompany) {
        this.industrialReferenceId = industrialReferenceId;
        this.industrialReferenceName = industrialReferenceName;
        this.industrialReferenceDesignation = industrialReferenceDesignation;
        this.industrialReferenceCompany = industrialReferenceCompany;
    }

    public int getIndustrialReferenceId() {
        return industrialReferenceId;
    }

    public void setIndustrialReferenceId(int industrialReferenceId) {
        this.industrialReferenceId = industrialReferenceId;
    }

    public String getIndustrialReferenceName() {
        return industrialReferenceName;
    }

    public void setIndustrialReferenceName(String industrialReferenceName) {
        this.industrialReferenceName = industrialReferenceName;
    }

    public String getIndustrialReferenceDesignation() {
        return industrialReferenceDesignation;
    }

    public void setIndustrialReferenceDesignation(String industrialReferenceDesignation) {
        this.industrialReferenceDesignation = industrialReferenceDesignation;
    }

    public String getIndustrialReferenceCompany() {
        return industrialReferenceCompany;
    }

    public void setIndustrialReferenceCompany(String industrialReferenceCompany) {
        this.industrialReferenceCompany = industrialReferenceCompany;
    }

}
