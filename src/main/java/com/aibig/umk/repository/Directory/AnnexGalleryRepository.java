package com.aibig.umk.repository.Directory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aibig.umk.model.Directory.AnnexGallery;

public interface AnnexGalleryRepository extends JpaRepository<AnnexGallery, Integer> {

    public AnnexGalleryRepository getAnnexGalleryByAnnexGalleryName(String annexGalleryName);

}
