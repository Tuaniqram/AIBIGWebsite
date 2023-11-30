package com.aibig.umk.services.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aibig.umk.model.User.Internship;
import com.aibig.umk.repository.User.InternshipRepository;

import java.util.List;

@Service
public class InternshipService {
    private final InternshipRepository internshipRepository;

    @Autowired
    public InternshipService(InternshipRepository internshipRepository) {
        this.internshipRepository = internshipRepository;
    }

    public List<Internship> getAllInternships() {
        return internshipRepository.findAll();
    }

    public void saveInternship(Internship internship) {
        internshipRepository.save(internship);
    }

    public Internship getInternshipById(int internId) {
        return internshipRepository.findById(internId).orElse(null);
    }

    public void deleteInternshipById(int internshipId) {
        internshipRepository.deleteById(internshipId);
    }
}