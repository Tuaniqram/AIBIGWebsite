package com.aibig.umk.services.Directory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aibig.umk.model.Directory.Programs;
import com.aibig.umk.repository.Directory.ProgramRepository;

@Service
public class ProgramService {
    private final ProgramRepository programRepository;

    @Autowired
    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public Programs saveProgram(Programs program) {
        return programRepository.save(program);
    }

    public Programs getProgramByShortName(String programShortName) {
        return programRepository.findByProgramShortName(programShortName);
    }

    public List<Programs> getAllPrograms() {
        return programRepository.findAll();
    }

    public Programs getProgramByProgramId(int programId) {
        return programRepository.findById(programId).orElse(null);
    }

    public Programs getProgramById(int programId) {
        return programRepository.findById(programId).orElse(null);
    }

    public void deleteProgramById(int programId) {
        programRepository.deleteById(programId);
    }

    public void updateProgram(Programs program) {
        Programs existingProgram = new Programs(program);
        programRepository.save(existingProgram);
    }
}
