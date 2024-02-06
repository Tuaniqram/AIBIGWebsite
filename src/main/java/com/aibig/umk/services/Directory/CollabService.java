package com.aibig.umk.services.Directory;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aibig.umk.repository.Directory.CollabRepository;
import com.aibig.umk.model.Directory.Collaborations;

@Service
public class CollabService {
    private final CollabRepository collabRepository;

    public CollabService(CollabRepository collabRepository) {
        this.collabRepository = collabRepository;
    }

    public void saveCollab(Collaborations collab) {
        collabRepository.save(collab);
    }

    public Collaborations getCollabById(int collabId) {
        return collabRepository.findById(collabId).orElse(null);
    }

    public void deleteCollabById(int collabId) {
        collabRepository.deleteById(collabId);
    }

    public Collaborations getCollabByCollabName(String collabName) {
        return collabRepository.findByCollaborationName(collabName);
    }

    public void updateCollab(Collaborations collab) {
        Collaborations existingCollaborations = new Collaborations(collab);
        collabRepository.save(existingCollaborations);
    }

    public List<Collaborations> getAllCollabs() {
        return collabRepository.findAll();
    }

}
