package com.aibig.umk.services.Directory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aibig.umk.model.Directory.AnnexGallery;
import com.aibig.umk.repository.Directory.AnnexGalleryRepository;

import java.util.List;

@Service
public class AnnexGalleryService {

    @Autowired
    private AnnexGalleryRepository annexGalleryRepository;

    public void saveAnnexGallery(AnnexGallery annexGallery) {
        annexGalleryRepository.save(annexGallery);
    }

    public void deleteAnnexGallery(int id) {
        annexGalleryRepository.deleteById(id);
    }

    public void updateAnnexGallery(AnnexGallery annexGallery) {
        AnnexGallery existingAnnexGallery = new AnnexGallery(annexGallery);
        annexGalleryRepository.save(existingAnnexGallery);
    }

    public List<AnnexGallery> getAllAnnexGallery() {
        return annexGalleryRepository.findAll();
    }
}