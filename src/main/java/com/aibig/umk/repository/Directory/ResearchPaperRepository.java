package com.aibig.umk.repository.Directory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aibig.umk.model.Directory.ResearchPaper;

public interface ResearchPaperRepository extends JpaRepository<ResearchPaper, Integer> {

    ResearchPaper findByResearchPaperTitle(String researchPaperTitle);

    List<ResearchPaper> findByResearchPaperCategoryOrderByResearchPaperDateDesc(String category);
}
