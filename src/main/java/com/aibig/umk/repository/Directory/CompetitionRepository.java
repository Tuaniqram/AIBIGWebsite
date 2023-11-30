package com.aibig.umk.repository.Directory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aibig.umk.model.Directory.Competition;

public interface CompetitionRepository extends JpaRepository<Competition, Integer> {

    Competition findByCompetitionName(String competitionName);

}
