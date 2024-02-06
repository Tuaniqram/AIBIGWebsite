package com.aibig.umk.repository.Directory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aibig.umk.model.Directory.IndustrialReference;

public interface IndustrialReferenceRepository extends JpaRepository<IndustrialReference, Integer> {
    IndustrialReference findByIndustrialReferenceName(String industrialReferenceName);

}
