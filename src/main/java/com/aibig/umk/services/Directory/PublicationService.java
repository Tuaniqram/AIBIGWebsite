package com.aibig.umk.services.Directory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aibig.umk.model.Directory.Publication;
import com.aibig.umk.repository.Directory.PublicationRepository;

@Service
public class PublicationService {

    private final PublicationRepository publicationRepository;

    @Autowired
    public PublicationService(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    public void savePublication(Publication publication) {
        publicationRepository.save(publication);
    }

    public Publication getPublicationById(int adminId) {
        return publicationRepository.findById(adminId).orElse(null);
    }

    public void deletePublicationById(int adminId) {
        publicationRepository.deleteById(adminId);
    }

    public Publication getPublicationByPublicationName(String publicationName) {
        return publicationRepository.findByPublicationName(publicationName);
    }

    public void updateadmin(Publication admin) {
        publicationRepository.save(admin);
    }

    public List<Publication> getAllPublication() {
        return publicationRepository.findAll();
    }
}
