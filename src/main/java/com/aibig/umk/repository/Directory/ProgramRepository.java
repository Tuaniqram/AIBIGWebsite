package com.aibig.umk.repository.Directory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aibig.umk.model.Directory.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer> {
    Program findByProgramShortName(String programShortName);
}
