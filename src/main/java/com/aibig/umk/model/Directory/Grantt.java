package com.aibig.umk.model.Directory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "grantt")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Grantt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "granttId")
    private int granttId;

    @Column(name = "granttName", columnDefinition = "LONGTEXT")
    private String granttName;

    @Column(name = "granttTitle", columnDefinition = "LONGTEXT")
    private String granttTitle;

    @Column(name = "granttDescription", columnDefinition = "LONGTEXT")
    private String granttDescription;

    @Column(name = "granttBudget")
    private String granttBudget;

    @Column(name = "granttFunder")
    private String granttFunder;

    @Column(name = "granttStartDate")
    private Date granttStartDate;

    @Column(name = "granttEndDate")
    private Date granttEndDate;

    @Column(name = "granttCategory")
    private String granttCategory;

}
