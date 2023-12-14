package com.aibig.umk.model.User;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "researchMember")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResearchMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "researchMemberId")
    private int researchMemberId;

    @Column(name = "researchMemberName", columnDefinition = "LONGTEXT")
    private String researchMemberName;

    @Column(name = "researchMemberRole", columnDefinition = "LONGTEXT")
    private String researchMemberRole;

    @Column(name = "researchMemberEmail")
    private String researchMemberEmail;

    @Column(name = "researchMemberPhone")
    private String researchMemberPhone;

    @Column(name = "researchMemberDepartment", columnDefinition = "LONGTEXT")
    private String researchMemberDepartment;

    @Column(name = "researchMemberCategory", columnDefinition = "LONGTEXT")
    private String researchMemberCategory;

    @Lob
    @Column(name = "researchMemberImage", columnDefinition = "MEDIUMBLOB")
    private byte[] researchMemberImage;

    @Transient
    private MultipartFile imageFile;

    public ResearchMember(ResearchMember researchMember) {
        this.researchMemberId = researchMember.getResearchMemberId();
        this.researchMemberName = researchMember.getResearchMemberName();
        this.researchMemberRole = researchMember.getResearchMemberRole();
        this.researchMemberEmail = researchMember.getResearchMemberEmail();
        this.researchMemberPhone = researchMember.getResearchMemberPhone();
        this.researchMemberDepartment = researchMember.getResearchMemberDepartment();
        this.researchMemberCategory = researchMember.getResearchMemberCategory();
        this.researchMemberImage = researchMember.getResearchMemberImage();
    }
}
