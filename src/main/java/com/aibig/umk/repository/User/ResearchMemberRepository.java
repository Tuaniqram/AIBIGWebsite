package com.aibig.umk.repository.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aibig.umk.model.User.ResearchMember;

@Repository
public interface ResearchMemberRepository extends JpaRepository<ResearchMember, Integer> {

    ResearchMember findByResearchMemberName(String researchMemberName);

    List<ResearchMember> findByResearchMemberCategoryOrderByResearchMemberName(String category);

}
