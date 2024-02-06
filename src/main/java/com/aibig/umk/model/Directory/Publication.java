package com.aibig.umk.model.Directory;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "publication")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publicationId")
    private int publicationId;

    @Column(name = "publicationTitle", columnDefinition = "LONGTEXT")
    private String publicationTitle;

    @Column(name = "publicationDescription", columnDefinition = "LONGTEXT")
    private String publicationDescription;

    @Column(name = "publicationDate")
    private Date publicationDate;

    @Column(name = "publicationAuthors", columnDefinition = "LONGTEXT")
    private String publicationAuthors;

    @Column(name = "publicationCategory")
    private String publicationCategory;

    public Publication(Publication publication) {
        this.publicationId = publication.getPublicationId();
        this.publicationTitle = publication.getPublicationTitle();
        this.publicationDescription = publication.getPublicationDescription();
        this.publicationDate = publication.getPublicationDate();
        this.publicationAuthors = publication.getPublicationAuthors();
        this.publicationCategory = publication.getPublicationCategory();
    }

}
