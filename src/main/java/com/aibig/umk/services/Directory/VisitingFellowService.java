package com.aibig.umk.services.Directory;

import java.util.List;
import org.springframework.stereotype.Service;
import com.aibig.umk.repository.Directory.VisitingFellowRepository;
import com.aibig.umk.model.Directory.VisitingFellow;

@Service
public class VisitingFellowService {
    private final VisitingFellowRepository visitingFellowRepository;

    public VisitingFellowService(VisitingFellowRepository visitingFellowRepository) {
        this.visitingFellowRepository = visitingFellowRepository;
    }

    public void saveVisitingFellow(VisitingFellow visitingFellow) {
        visitingFellowRepository.save(visitingFellow);
    }

    public VisitingFellow findByVisitingFellowName(String visitingFellowName) {
        return visitingFellowRepository.findByVisitingFellowName(visitingFellowName);
    }

    public VisitingFellow findByVisitingFellowId(int visitingFellowId) {
        return visitingFellowRepository.findById(visitingFellowId).orElse(null);
    }

    public void deleteVisitingFellowById(int visitingFellowId) {
        visitingFellowRepository.deleteById(visitingFellowId);
    }

    public void updateVisitingFellow(VisitingFellow visitingFellow) {
        VisitingFellow existingVisitingFellow = new VisitingFellow(visitingFellow);
        visitingFellowRepository.save(existingVisitingFellow);
    }

    public List<VisitingFellow> findAllVisitingFellow() {
        return visitingFellowRepository.findAll();
    }

}
