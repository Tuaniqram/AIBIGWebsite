package com.aibig.umk.repository.Directory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aibig.umk.model.Directory.MouMoa;

public interface MouMoaRepository extends JpaRepository<MouMoa, Integer> {

    @Query("SELECT m FROM MouMoa m ORDER BY m.MouMoaId DESC")
    MouMoa findTopByOrderByMouMoaIdDesc();

}
