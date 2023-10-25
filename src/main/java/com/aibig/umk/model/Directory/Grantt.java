package com.aibig.umk.model.Directory;

import jakarta.persistence.*;

@Entity
@Table(name = "grantt")
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
    private String granttStartDate;

    @Column(name = "granttEndDate")
    private String granttEndDate;

    public Grantt() {
    }

    public Grantt(int granttId, String granttName, String granttTitle, String granttDescription, String granttBudget,
            String granttFunder, String granttStartDate, String granttEndDate) {
        this.granttId = granttId;
        this.granttName = granttName;
        this.granttTitle = granttTitle;
        this.granttDescription = granttDescription;
        this.granttBudget = granttBudget;
        this.granttFunder = granttFunder;
        this.granttStartDate = granttStartDate;
        this.granttEndDate = granttEndDate;
    }

}
