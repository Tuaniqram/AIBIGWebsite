package com.aibig.umk.services.Directory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aibig.umk.model.Directory.AnnexAssociation;
import com.aibig.umk.model.Directory.AnnexGallery;
import com.aibig.umk.repository.Directory.AnnexAssociationRepository;
import com.aibig.umk.repository.Directory.AnnexGalleryRepository;

import java.util.List;
import java.util.ArrayList;

@Service
public class AnnexGalleryService {

    @Autowired
    private AnnexGalleryRepository annexGalleryRepository;

    @Autowired
    private AnnexAssociationRepository annexAssociationRepository;

    public void saveAnnexGallery(AnnexGallery annexGallery) {
        annexGalleryRepository.save(annexGallery);
        AnnexAssociation temp = new AnnexAssociation();
        temp.setAnnexGalleryFirst(annexGallery);
        temp.setAnnexGallerySecond(annexGallery);
        annexAssociationRepository.save(temp);
    }

    public void savenewImages(AnnexGallery newImages, AnnexGallery annexGallery) {
        annexGalleryRepository.save(newImages);
        AnnexAssociation temp = new AnnexAssociation();
        temp.setAnnexGalleryFirst(annexGallery);
        temp.setAnnexGallerySecond(newImages);
        annexAssociationRepository.save(temp);
    }

    public AnnexGallery getFirstImage(AnnexGallery gallery) {
        return annexAssociationRepository.getAnnexGalleryByAnnexGalleryFirst(gallery);
    }

    public AnnexGallery getSecondImage(AnnexGallery gallery) {
        return annexAssociationRepository.getAnnexGalleryByAnnexGallerySecond(gallery);
    }

    public AnnexGallery findByAnnexGalleryId(int id) {
        return annexGalleryRepository.findById(id).orElse(null);
    }

    public AnnexAssociation findAnnexAssociationById(AnnexGallery gallery) {
        return annexAssociationRepository.getAnnexAssociationByAnnexGallerySecond(gallery);

    }

    public void deleteAnnexGallery(int id, AnnexGallery annexGallery) {
        annexAssociationRepository.deleteById(id);
        annexGalleryRepository.deleteById(annexGallery.getAnnexGalleryId());
    }

    public void updateAnnexGallery(AnnexGallery annexGallery) {
        AnnexGallery existingAnnexGallery = new AnnexGallery(annexGallery);
        annexGalleryRepository.save(existingAnnexGallery);
    }

    public List<AnnexGallery> getAllAnnexGallery() {
        return annexGalleryRepository.findAll();
    }

    public List<AnnexAssociation> getAllAnnexAssociation() {
        List<AnnexAssociation> annexAssociationList = annexAssociationRepository.findAll();
        List<AnnexAssociation> FilteredGallery = new ArrayList();

        for (AnnexAssociation annexAssociation : annexAssociationList) {
            if (annexAssociation.getAnnexGalleryFirst().getAnnexGalleryId() == annexAssociation.getAnnexGallerySecond()
                    .getAnnexGalleryId()) {
                FilteredGallery.add(annexAssociation);
            }
        }
        return FilteredGallery;
    }

    public List<AnnexAssociation> getFilteredAssociation(AnnexGallery annexGallery) {
        List<AnnexAssociation> annexAssociationList = annexAssociationRepository.findAll();
        List<AnnexAssociation> FilteredGallery = new ArrayList();

        for (AnnexAssociation annexAssociation : annexAssociationList) {
            if (annexAssociation.getAnnexGalleryFirst().getAnnexGalleryId() == annexGallery.getAnnexGalleryId()) {
                FilteredGallery.add(annexAssociation);
            }
        }
        return FilteredGallery;
    }

    public List<AnnexAssociation> getAllImages() {
        return annexAssociationRepository.findAll();
    }

    public List<AnnexGallery> getAnnexGalleryViaAssociation() {
        List<AnnexAssociation> annexAssociationList = annexAssociationRepository.findAll();
        List<AnnexGallery> FilteredGallery = new ArrayList();

        for (AnnexAssociation annexAssociation : annexAssociationList) {
            if (annexAssociation.getAnnexGalleryFirst().getAnnexGalleryId() == annexAssociation.getAnnexGallerySecond()
                    .getAnnexGalleryId()) {
                FilteredGallery.add(annexAssociation.getAnnexGalleryFirst());
            }
        }
        return FilteredGallery;
    }

}