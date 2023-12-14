package com.aibig.umk.services.Directory;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aibig.umk.repository.Directory.ScientificAdvisoryRepository;
import com.aibig.umk.model.Directory.ScientificAdvisory;

@Service
public class ScientificAdvisoryService {

    private final ScientificAdvisoryRepository scientificAdvisoryRepository;

    public ScientificAdvisoryService(ScientificAdvisoryRepository scientificAdvisoryRepository) {
        this.scientificAdvisoryRepository = scientificAdvisoryRepository;
    }

    public void saveScientificAdvisory(ScientificAdvisory scientificAdvisory) {
        scientificAdvisoryRepository.save(scientificAdvisory);
    }

    public ScientificAdvisory getScientificAdvisoryById(int scientificAdvisoryId) {
        return scientificAdvisoryRepository.findById(scientificAdvisoryId).orElse(null);
    }

    public void deleteScientificAdvisoryById(int scientificAdvisoryId) {
        scientificAdvisoryRepository.deleteById(scientificAdvisoryId);
    }

    public ScientificAdvisory getScientificAdvisoryByScientificAdvisoryName(String scientificAdvisoryName) {
        return scientificAdvisoryRepository.findByScientificAdvisoryName(scientificAdvisoryName);
    }

    public void updateScientificAdvisory(ScientificAdvisory scientificAdvisory) {
        ScientificAdvisory existingScientificAdvisory = new ScientificAdvisory(scientificAdvisory);
        scientificAdvisoryRepository.save(existingScientificAdvisory);
    }

    public List<ScientificAdvisory> getAllScientificAdvisories() {
        return scientificAdvisoryRepository.findAll();
    }

}
