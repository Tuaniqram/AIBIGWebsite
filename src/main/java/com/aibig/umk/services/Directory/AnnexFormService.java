package com.aibig.umk.services.Directory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aibig.umk.model.Directory.AnnexForm;
import com.aibig.umk.repository.Directory.AnnexFormRepository;

import java.util.List;

@Service
public class AnnexFormService {

    @Autowired
    private AnnexFormRepository annexFormRepository;

    public void saveAnnexForm(AnnexForm annexForm) {
        annexFormRepository.save(annexForm);
    }

    public void deleteAnnexForm(int id) {
        annexFormRepository.deleteById(id);
    }

    public void updateAnnexForm(AnnexForm annexForm) {
        AnnexForm existingAnnexForm = new AnnexForm(annexForm);
        annexFormRepository.save(existingAnnexForm);
    }

    public List<AnnexForm> getAllAnnexForm() {
        return annexFormRepository.findAll();
    }

}
