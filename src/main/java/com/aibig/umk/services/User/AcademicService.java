package com.aibig.umk.services.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aibig.umk.model.User.Academic;
import com.aibig.umk.repository.User.AcademicRepository;

@Service
public class AcademicService {

    private final AcademicRepository academicRepository;

    @Autowired
    public AcademicService(AcademicRepository academicRepository) {
        this.academicRepository = academicRepository;
    }

    public void saveAcademic(Academic academic) {
        academicRepository.save(academic);
    }

    public Academic getAcademicById(int academicId) {
        return academicRepository.findById(academicId).orElse(null);
    }

    public void deleteAcademicById(int academicId) {
        academicRepository.deleteById(academicId);
    }

    public Academic getAcademicByAcademicName(String academicName) {
        return academicRepository.findByAcademicName(academicName);
    }

    public void updateAcademic(Academic Academic) {
        academicRepository.save(Academic);
    }

    public List<Academic> getAllAcademics() {
        return academicRepository.findAll();
    }

}
