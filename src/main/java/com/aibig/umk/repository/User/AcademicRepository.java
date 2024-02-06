package com.aibig.umk.repository.User;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aibig.umk.model.User.Academic;

public interface AcademicRepository extends JpaRepository<Academic, Integer> {

    Academic findByAcademicName(String academicName);

}
