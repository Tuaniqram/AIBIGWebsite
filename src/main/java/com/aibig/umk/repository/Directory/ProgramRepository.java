package com.aibig.umk.repository.Directory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aibig.umk.model.Directory.Program;

public interface ProgramRepository extends JpaRepository<Program, Integer> {
    Program findByProgramShortName(String programShortName);
}
