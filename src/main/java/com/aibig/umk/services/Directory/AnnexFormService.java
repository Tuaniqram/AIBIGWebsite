package com.aibig.umk.services.Directory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aibig.umk.model.Directory.AnnexForm;
import com.aibig.umk.repository.Directory.AnnexFormRepository;

import java.io.IOException;
import java.util.List;

@Service
public class AnnexFormService {

    @Autowired
    private AnnexFormRepository annexFormRepository;

    public void saveAnnexForm(AnnexForm annexForm, MultipartFile pdffile) throws IOException {
        annexForm.setAnnexFile(pdffile.getBytes());
        annexFormRepository.save(annexForm);
    }

    public AnnexForm getAnnexForm(int index) {
        return annexFormRepository.getReferenceById(index);
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
