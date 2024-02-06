package com.aibig.umk.repository.Directory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aibig.umk.model.Directory.VisitingFellow;

public interface VisitingFellowRepository extends JpaRepository<VisitingFellow, Integer> {

    VisitingFellow findByVisitingFellowName(String visitingFellowName);
}
