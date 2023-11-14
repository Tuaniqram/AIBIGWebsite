package com.aibig.umk.repository.Directory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aibig.umk.model.Directory.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Integer> {

    Publication findByPublicationName(String publicationName);

    @Query("SELECT MONTH(p.publicationDate), COUNT(p) FROM Publication p GROUP BY MONTH(p.publicationDate)")
    List<Object[]> getMonthlyPublicationCount();
}