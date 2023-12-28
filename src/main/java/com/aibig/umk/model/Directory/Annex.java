package com.aibig.umk.model.Directory;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Annex")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Annex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "annexId")
    private int annexId;

    @Column(name = "annexName")
    private String annexName;

    @Column(name = "annexType")
    private String annexType;

    @Column(name = "annexImage", columnDefinition = "MEDIUMBLOB")
    private byte[] annexImage;

    @Transient
    private MultipartFile imageFile;

    public Annex(Annex annex) {
        this.annexId = annex.getAnnexId();
        this.annexName = annex.getAnnexName();
        this.annexType = annex.getAnnexType();
        this.annexImage = annex.getAnnexImage();
    }

}
