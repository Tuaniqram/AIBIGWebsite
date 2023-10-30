package com.aibig.umk.repository.Directory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aibig.umk.model.Directory.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Integer> {

    Publication findByPublicationName(String publicationName);

}