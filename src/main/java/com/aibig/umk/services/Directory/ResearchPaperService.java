package com.aibig.umk.services.Directory;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aibig.umk.repository.Directory.ResearchPaperRepository;
import com.aibig.umk.model.Directory.ResearchPaper;

@Service
public class ResearchPaperService {

    private final ResearchPaperRepository researchPaperRepository;

    public ResearchPaperService(ResearchPaperRepository researchPaperRepository) {
        this.researchPaperRepository = researchPaperRepository;
    }

    public void saveResearchPaper(ResearchPaper researchPaper) {
        researchPaperRepository.save(researchPaper);
    }

    public ResearchPaper getResearchPaperById(int researchPaperId) {
        return researchPaperRepository.findById(researchPaperId).orElse(null);
    }

    public void deleteResearchPaperById(int researchPaperId) {
        researchPaperRepository.deleteById(researchPaperId);
    }

    public ResearchPaper getResearchPaperByResearchPaperTittle(String researchPaperName) {
        return researchPaperRepository.findByResearchPaperTitle(researchPaperName);
    }

    public void updateResearchPaper(ResearchPaper researchPaper) {
        researchPaperRepository.save(researchPaper);
    }

    public List<ResearchPaper> getAllResearchPapers() {
        return researchPaperRepository.findAll();
    }

}
