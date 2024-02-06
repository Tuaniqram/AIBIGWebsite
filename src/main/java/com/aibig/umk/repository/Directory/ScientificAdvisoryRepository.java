package com.aibig.umk.repository.Directory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aibig.umk.model.Directory.ScientificAdvisory;

public interface ScientificAdvisoryRepository extends JpaRepository<ScientificAdvisory, Integer> {

    ScientificAdvisory findByScientificAdvisoryName(String scientificAdvisoryName);

}