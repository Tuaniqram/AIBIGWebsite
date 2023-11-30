package com.aibig.umk.model.Directory;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MouMoa")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MouMoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MouMoaId")
    private int MouMoaId;

    @Column(name = "MouMoaOrganizationName")
    private String MouMoaOrganizationName;

    @Column(name = "MouMoaName")
    private String MouMoaName;

    @Column(name = "MouMoaDescription", columnDefinition = "LONGTEXT")
    private String MouMoaDescription;

    @Column(name = "MouMoaDate")
    private String MouMoaDate;

    @Column(name = "MouMoaLink")
    private String MouMoaLink;

    @Lob
    @Column(name = "MouMoaImage", columnDefinition = "MEDIUMBLOB")
    private byte[] MouMoaImage;

    @Transient
    private MultipartFile imageFile;

}
