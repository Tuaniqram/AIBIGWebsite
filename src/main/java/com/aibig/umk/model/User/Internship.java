package com.aibig.umk.model.User;

import org.springframework.web.multipart.MultipartFile;
import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "internship")
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

    // Constructors

    public Internship() {
    }

    // Getters and Setters

    public int getIntern_id() {
        return intern_id;
    }

    public void setIntern_id(int intern_id) {
        this.intern_id = intern_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Date getInternStart() {
        return internStart;
    }

    public void setInternStart(Date internStart) {
        this.internStart = internStart;
    }

    public Date getInternEnd() {
        return internEnd;
    }

    public void setInternEnd(Date internEnd) {
        this.internEnd = internEnd;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }
}
