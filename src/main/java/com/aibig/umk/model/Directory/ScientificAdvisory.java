package com.aibig.umk.model.Directory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "scientificAdvisory")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

}
