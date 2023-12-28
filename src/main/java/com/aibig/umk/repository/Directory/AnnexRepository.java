package com.aibig.umk.repository.Directory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aibig.umk.model.Directory.Annex;

public interface AnnexRepository extends JpaRepository<Annex, Integer> {

    public Annex getAnnexByAnnexName(String annexName);

}
