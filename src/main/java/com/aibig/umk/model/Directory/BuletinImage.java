package com.aibig.umk.model.Directory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "BuletinImage")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BuletinImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buletinImageId")
    private int buletinImageId;

    // on delete cascade delete all files
    @ManyToOne
    @JoinColumn(name = "buletinFileId", referencedColumnName = "buletinFileId", nullable = false)
    private BuletinFile buletinFileId;

    @Column(name = "buletinPage")
    private int buletinPage;

    @Lob
    @Column(name = "buletinImage", columnDefinition = "MEDIUMBLOB")
    private byte[] buletinImage;

    @Transient
    private MultipartFile imageFile;

    public BuletinImage(BuletinImage buletinImage) {
        this.buletinImageId = buletinImage.getBuletinImageId();
        this.buletinFileId = buletinImage.getBuletinFileId();
        this.buletinImage = buletinImage.getBuletinImage();
    }

}
