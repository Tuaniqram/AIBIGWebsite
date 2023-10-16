// package com.aibig.umk.services;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.aibig.umk.model.User.Internship;
// import com.aibig.umk.repository.InternshipRepository;

// import java.util.List;

// @Service
// public class InternshipService {
// private final InternshipRepository internshipRepository;

// @Autowired
// public InternshipService(InternshipRepository internshipRepository) {
// this.internshipRepository = internshipRepository;
// }

// public List<Internship> getAllInternships() {
// return internshipRepository.findAll();
// }
// }