package com.aibig.umk.repository.Directory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aibig.umk.model.Directory.Programs;

@Repository
public interface ProgramRepository extends JpaRepository<Programs, Integer> {
    Programs findByProgramShortName(String programShortName);
}
