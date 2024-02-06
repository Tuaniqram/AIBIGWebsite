package com.aibig.umk.model.Directory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "industrialReference")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    public IndustrialReference(IndustrialReference industrialReference) {
        this.industrialReferenceId = industrialReference.getIndustrialReferenceId();
        this.industrialReferenceName = industrialReference.getIndustrialReferenceName();
        this.industrialReferenceDesignation = industrialReference.getIndustrialReferenceDesignation();
        this.industrialReferenceCompany = industrialReference.getIndustrialReferenceCompany();
    }

}
