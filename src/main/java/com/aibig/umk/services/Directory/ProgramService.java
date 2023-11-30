package com.aibig.umk.services.Directory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aibig.umk.model.Directory.Program;
import com.aibig.umk.repository.Directory.ProgramRepository;

@Service
public class ProgramService {
    private final ProgramRepository programRepository;

    @Autowired
    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public Program saveProgram(Program program) {
        return programRepository.save(program);
    }

    public Program getProgramByShortName(String programShortName) {
        return programRepository.findByProgramShortName(programShortName);
    }

    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    public Program getProgramByProgramId(int programId) {
        return programRepository.findById(programId).orElse(null);
    }

    public Program getProgramById(int programId) {
        return programRepository.findById(programId).orElse(null);
    }

    public void deleteProgramById(int programId) {
        programRepository.deleteById(programId);
    }
}
