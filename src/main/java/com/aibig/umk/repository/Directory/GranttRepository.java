package com.aibig.umk.repository.Directory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aibig.umk.model.Directory.Grantt;

@Repository
public interface GranttRepository extends JpaRepository<Grantt, Integer> {

    Grantt findByGranttName(String granttName);
}
