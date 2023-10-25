package com.aibig.umk.model.Directory;

import jakarta.persistence.*;

@Entity
@Table(name = "publication")
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publicationId")
    private int publicationId;

    @Column(name = "publicationName", columnDefinition = "LONGTEXT")
    private String publicationName;

    @Column(name = "publicationTitle", columnDefinition = "LONGTEXT")
    private String publicationTitle;

    @Column(name = "publicationDescription", columnDefinition = "LONGTEXT")
    private String publicationDescription;

    @Column(name = "publicationDate")
    private String publicationDate;

    @Column(name = "publicationAuthors")
    private String publicationAuthors;

    @Column(name = "publicationLink")
    private String publicationLink;

    public Publication() {
    }

    public Publication(int publicationId, String publicationName, String publicationTitle,
            String publicationDescription, String publicationDate, String publicationAuthors, String publicationLink) {
        this.publicationId = publicationId;
        this.publicationName = publicationName;
        this.publicationTitle = publicationTitle;
        this.publicationDescription = publicationDescription;
        this.publicationDate = publicationDate;
        this.publicationAuthors = publicationAuthors;
        this.publicationLink = publicationLink;
    }

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    public String getPublicationName() {
        return publicationName;
    }

    public void setPublicationName(String publicationName) {
        this.publicationName = publicationName;
    }

    public String getPublicationTitle() {
        return publicationTitle;
    }

    public void setPublicationTitle(String publicationTitle) {
        this.publicationTitle = publicationTitle;
    }

    public String getPublicationDescription() {
        return publicationDescription;
    }

    public void setPublicationDescription(String publicationDescription) {
        this.publicationDescription = publicationDescription;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPublicationAuthors() {
        return publicationAuthors;
    }

    public void setPublicationAuthors(String publicationAuthors) {
        this.publicationAuthors = publicationAuthors;
    }

    public String getPublicationLink() {
        return publicationLink;
    }

    public void setPublicationLink(String publicationLink) {
        this.publicationLink = publicationLink;
    }

}
