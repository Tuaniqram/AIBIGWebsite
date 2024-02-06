package com.aibig.umk.repository.Directory;

import com.aibig.umk.model.Directory.AnnexAssociation;
import com.aibig.umk.model.Directory.AnnexGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnnexAssociationRepository extends JpaRepository<AnnexAssociation, Integer> {

    @Query(value = "SELECT a FROM AnnexAssociation a WHERE a.annexGalleryFirst = :galleryFirst")
    AnnexAssociation getAnnexAssociationByAnnexGalleryFirst(@Param("galleryFirst") AnnexGallery annexGalleryFirst);

    @Query(value = "SELECT a FROM AnnexAssociation a WHERE a.annexGallerySecond = :gallerySecond")
    AnnexAssociation getAnnexAssociationByAnnexGallerySecond(@Param("gallerySecond") AnnexGallery annexGallerySecond);

    @Query(value = "SELECT a.annexGalleryFirst FROM AnnexAssociation a WHERE a.annexGalleryFirst = :galleryFirst")
    AnnexGallery getAnnexGalleryByAnnexGalleryFirst(@Param("galleryFirst") AnnexGallery annexGalleryFirst);

    @Query(value = "SELECT a.annexGallerySecond FROM AnnexAssociation a WHERE a.annexGallerySecond = :gallerySecond")
    AnnexGallery getAnnexGalleryByAnnexGallerySecond(@Param("gallerySecond") AnnexGallery annexGallerySecond);
}
