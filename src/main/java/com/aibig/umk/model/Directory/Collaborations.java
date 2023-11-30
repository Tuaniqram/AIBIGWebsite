package com.aibig.umk.model.Directory;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "collaborations")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Collaborations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collaborationId")
    private int collaborationId;

    @Column(name = "collaborationName")
    private String collaborationName;

    @Column(name = "collaborationDescription", columnDefinition = "LONGTEXT")
    private String collaborationDescription;

    @Column(name = "collaborartionDate")
    private Date collaborationDate;

}
