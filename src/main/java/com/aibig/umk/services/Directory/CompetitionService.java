package com.aibig.umk.services.Directory;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aibig.umk.repository.Directory.CompetitionRepository;
import com.aibig.umk.model.Directory.Competition;

@Service
public class CompetitionService {
    private final CompetitionRepository competitionRepository;

    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public void saveCompetition(Competition competition) {
        competitionRepository.save(competition);
    }

    public void deleteCompetitionById(int competitionId) {
        competitionRepository.deleteById(competitionId);
    }

    public Competition getCompetitionByCompetitionName(String competitionName) {
        return competitionRepository.findByCompetitionName(competitionName);
    }

    public void updateCompetition(Competition competition) {
        Competition existingCompetition = new Competition(competition);
        competitionRepository.save(existingCompetition);
    }

    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    public Competition getCompetitionById(int competitionId) {
        return competitionRepository.findById(competitionId).orElse(null);
    }

}
