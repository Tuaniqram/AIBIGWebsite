package com.aibig.umk.model.Directory;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "AnnexGallery")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnnexGallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "annexGalleryId")
    private int annexGalleryId;

    @Column(name = "annexGalleryName")
    private String annexGalleryName;

    @Column(name = "annexGalleryShortName")
    private String annexGalleryShortName;

    @Column(name = "annexGalleryDate")
    private String annexGalleryDate;

    @Column(name = "annexGalleryType")
    private String annexGalleryType;

    @Column(name = "annexGalleryNumber")
    private int annexGalleryNumber;

    @Column(name = "annexGalleryImage", columnDefinition = "MEDIUMBLOB")
    private byte[] annexGalleryImage;

    @Transient
    private MultipartFile imageFile;

    public AnnexGallery(AnnexGallery annexGallery) {
        this.annexGalleryId = annexGallery.getAnnexGalleryId();
        this.annexGalleryName = annexGallery.getAnnexGalleryName();
        this.annexGalleryType = annexGallery.getAnnexGalleryType();
        this.annexGalleryImage = annexGallery.getAnnexGalleryImage();
    }

}
