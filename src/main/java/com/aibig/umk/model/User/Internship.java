package com.aibig.umk.model.User;

import org.springframework.web.multipart.MultipartFile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "internship")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int intern_id;

    @Column(name = "name")
    private String name;

    @Column(name = "university")
    private String university;

    @Column(name = "project", columnDefinition = "LONGTEXT")
    private String project;

    @Column(name = "intern_start")
    private Date internStart;

    @Column(name = "intern_end")
    private Date internEnd;

    @Lob
    @Column(name = "image", columnDefinition = "MEDIUMBLOB")
    private byte[] image;

    @Transient
    private MultipartFile imageFile;

    public Internship(Internship internship) {
        this.intern_id = internship.getIntern_id();
        this.name = internship.getName();
        this.university = internship.getUniversity();
        this.project = internship.getProject();
        this.internStart = internship.getInternStart();
        this.internEnd = internship.getInternEnd();
        this.image = internship.getImage();
    }
}
