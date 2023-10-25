package com.aibig.umk.model.Directory;

import jakarta.persistence.*;

@Entity
@Table(name = "scientificAdvisory")
public class ScientificAdvisory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scientificAdvisoryId")
    private int scientificAdvisoryId;

    @Column(name = "scientificAdvisoryName")
    private String scientificAdvisoryName;

    @Column(name = "scientificAdvisoryWebsite")
    private String scientificAdvisoryWebsite;

    @Column(name = "scientificAdvisoryDesignation", columnDefinition = "LONGTEXT")
    private String scientificAdvisoryDesignation;

    @Column(name = "scientificAdvisoryOrganization", columnDefinition = "LONGTEXT")
    private String scientificAdvisoryOrganization;

    public ScientificAdvisory() {
    }

    public ScientificAdvisory(int scientificAdvisoryId, String scientificAdvisoryName,
            String scientificAdvisoryWebsite, String scientificAdvisoryDesignation,
            String scientificAdvisoryOrganization) {
        this.scientificAdvisoryId = scientificAdvisoryId;
        this.scientificAdvisoryName = scientificAdvisoryName;
        this.scientificAdvisoryWebsite = scientificAdvisoryWebsite;
        this.scientificAdvisoryDesignation = scientificAdvisoryDesignation;
        this.scientificAdvisoryOrganization = scientificAdvisoryOrganization;
    }

    public int getScientificAdvisoryId() {
        return scientificAdvisoryId;
    }

    public void setScientificAdvisoryId(int scientificAdvisoryId) {
        this.scientificAdvisoryId = scientificAdvisoryId;
    }

    public String getScientificAdvisoryName() {
        return scientificAdvisoryName;
    }

    public void setScientificAdvisoryName(String scientificAdvisoryName) {
        this.scientificAdvisoryName = scientificAdvisoryName;
    }

    public String getScientificAdvisoryWebsite() {
        return scientificAdvisoryWebsite;
    }

    public void setScientificAdvisoryWebsite(String scientificAdvisoryWebsite) {
        this.scientificAdvisoryWebsite = scientificAdvisoryWebsite;
    }

    public String getScientificAdvisoryDesignation() {
        return scientificAdvisoryDesignation;
    }

    public void setScientificAdvisoryDesignation(String scientificAdvisoryDesignation) {
        this.scientificAdvisoryDesignation = scientificAdvisoryDesignation;
    }

    public String getScientificAdvisoryOrganization() {
        return scientificAdvisoryOrganization;
    }

    public void setScientificAdvisoryOrganization(String scientificAdvisoryOrganization) {
        this.scientificAdvisoryOrganization = scientificAdvisoryOrganization;
    }

}
