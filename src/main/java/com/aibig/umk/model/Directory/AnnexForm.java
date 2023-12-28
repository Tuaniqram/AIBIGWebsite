package com.aibig.umk.model.Directory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "AnnexForm")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnnexForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "annexFormId")
    private int annexFormId;

    @Column(name = "annexFormName")
    private String annexFormName;

    @Column(name = "annexFormType")
    private String annexFormType;

    @Column(name = "annexFile", columnDefinition = "MEDIUMBLOB")
    private byte[] annexFile;

    public AnnexForm(AnnexForm annexForm) {
        this.annexFormId = annexForm.getAnnexFormId();
        this.annexFormName = annexForm.getAnnexFormName();
        this.annexFormType = annexForm.getAnnexFormType();
        this.annexFile = annexForm.getAnnexFile();
    }

}
