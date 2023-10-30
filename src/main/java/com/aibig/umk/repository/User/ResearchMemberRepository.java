package com.aibig.umk.repository.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aibig.umk.model.User.ResearchMember;

@Repository
public interface ResearchMemberRepository extends JpaRepository<ResearchMember, Integer> {

    ResearchMember findByResearchMemberName(String researchMemberName);

}
