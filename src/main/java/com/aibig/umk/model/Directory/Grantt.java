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

    @Column(name = "granttTitle", columnDefinition = "LONGTEXT")
    private String granttTitle;

    @Column(name = "granttResearcher", columnDefinition = "LONGTEXT")
    private String granttResearcher;

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

    public Grantt(Grantt grantt) {
        this.granttId = grantt.getGranttId();
        this.granttTitle = grantt.getGranttTitle();
        this.granttResearcher = grantt.getGranttResearcher();
        this.granttBudget = grantt.getGranttBudget();
        this.granttFunder = grantt.getGranttFunder();
        this.granttStartDate = grantt.getGranttStartDate();
        this.granttEndDate = grantt.getGranttEndDate();
        this.granttCategory = grantt.getGranttCategory();
    }

}
