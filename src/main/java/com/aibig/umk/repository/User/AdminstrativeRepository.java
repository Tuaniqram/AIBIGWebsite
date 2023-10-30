package com.aibig.umk.repository.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aibig.umk.model.User.Adminstrative;

@Repository
public interface AdminstrativeRepository extends JpaRepository<Adminstrative, Integer> {

    Adminstrative findByAdminName(String adminName);

}
