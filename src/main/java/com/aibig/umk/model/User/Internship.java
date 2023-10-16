package com.aibig.umk.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "internship")
public class Internship {
    @Id
    @GeneratedValue
    private int intern_id;

    @Column(name = "name")
    private String name;

    @Column(name = "university")
    private String university;

    @Column(name = "project")
    private String project;

    @Column(name = "intern_start")
    private Date internStart;

    @Column(name = "intern_end")
    private Date internEnd;

    @Lob
    private byte[] image;
    // Constructors, getters, and setters

    public Internship() {
    }

    public Internship(int intern_id, String name, String university, String project, Date internStart, Date internEnd,
            byte[] image) {
        this.intern_id = intern_id;
        this.name = name;
        this.university = university;
        this.project = project;
        this.internStart = internStart;
        this.internEnd = internEnd;
        this.image = image;
    }

    // Getter
    public int getIntern_id() {
        return intern_id;
    }

    public String getName() {
        return name;
    }

    public String getUniversity() {
        return university;
    }

    public String getProject() {
        return project;
    }

    public Date getInternStart() {
        return internStart;
    }

    public Date getInternEnd() {
        return internEnd;
    }

    public byte[] getImage() {
        return image;
    }
}