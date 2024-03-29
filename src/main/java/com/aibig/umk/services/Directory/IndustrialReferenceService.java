package com.aibig.umk.services.Directory;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aibig.umk.model.Directory.IndustrialReference;
import com.aibig.umk.repository.Directory.IndustrialReferenceRepository;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Service
public class IndustrialReferenceService {
    private final IndustrialReferenceRepository industrialReferenceRepository;

    public IndustrialReferenceService(IndustrialReferenceRepository industrialReferenceRepository) {
        this.industrialReferenceRepository = industrialReferenceRepository;
    }

    public void saveIndustrialReference(IndustrialReference industrialReference) {
        industrialReferenceRepository.save(industrialReference);
    }

    public IndustrialReference findByIndustrialReferenceId(int industrialReferenceId) {
        return industrialReferenceRepository.findById(industrialReferenceId).orElse(null);
    }

    public void deleteIndustrialReferenceById(int industrialReferenceId) {
        industrialReferenceRepository.deleteById(industrialReferenceId);
    }

    public void updateIndustrialReference(IndustrialReference industrialReference) {
        IndustrialReference existingIndustrialReference = new IndustrialReference(industrialReference);
        industrialReferenceRepository.save(existingIndustrialReference);
    }

    public IndustrialReference findByIndustrialReferenceName(String industrialReferenceName) {
        return industrialReferenceRepository.findByIndustrialReferenceName(industrialReferenceName);
    }

    public List<IndustrialReference> findAllIndustrialReference() {
        return industrialReferenceRepository.findAll();
    }

}
