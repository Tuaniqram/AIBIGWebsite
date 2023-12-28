package com.aibig.umk.repository.Directory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aibig.umk.model.Directory.AnnexForm;

public interface AnnexFormRepository extends JpaRepository<AnnexForm, Integer> {

    public AnnexForm getAnnexFormByAnnexFormName(String annexFormName);

}
