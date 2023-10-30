package com.aibig.umk.model.Directory;

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

    @Column(name = "publicationName", columnDefinition = "LONGTEXT")
    private String publicationName;

    @Column(name = "publicationTitle", columnDefinition = "LONGTEXT")
    private String publicationTitle;

    @Column(name = "publicationDescription", columnDefinition = "LONGTEXT")
    private String publicationDescription;

    @Column(name = "publicationDate")
    private String publicationDate;

    @Column(name = "publicationAuthors", columnDefinition = "LONGTEXT")
    private String publicationAuthors;

    @Column(name = "publicationLink")
    private String publicationLink;

    @Column(name = "publicationCategory")
    private String publicationCategory;

}
