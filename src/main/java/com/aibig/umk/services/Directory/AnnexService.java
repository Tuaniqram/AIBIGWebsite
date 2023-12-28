package com.aibig.umk.services.Directory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aibig.umk.model.Directory.Annex;
import com.aibig.umk.repository.Directory.AnnexRepository;

import java.util.List;

@Service
public class AnnexService {

    @Autowired
    private AnnexRepository annexRepository;

    public void saveAnnex(Annex annex) {
        annexRepository.save(annex);
    }

    public void deleteAnnex(int id) {
        annexRepository.deleteById(id);
    }

    public void updateAnnex(Annex annex) {
        Annex existingAnnex = new Annex(annex);
        annexRepository.save(existingAnnex);
    }

    public List<Annex> getAllAnnex() {
        return annexRepository.findAll();
    }

}
