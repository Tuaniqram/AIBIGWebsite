package com.aibig.umk.model.Directory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "AnnexAssociation")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnnexAssociation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "annexGalleryId")
    private int annexGalleryId;

    @ManyToOne
    @JoinColumn(name = "annexGalleryFirst")
    private AnnexGallery annexGalleryFirst;

    @ManyToOne
    @JoinColumn(name = "annexGallerySecond")
    private AnnexGallery annexGallerySecond;

    public AnnexAssociation(AnnexAssociation annexAssociation) {
        this.annexGalleryId = annexAssociation.getAnnexGalleryId();
        this.annexGalleryFirst = annexAssociation.getAnnexGalleryFirst();
        this.annexGallerySecond = annexAssociation.getAnnexGallerySecond();
    }

}
