package com.aibig.umk.repository.Directory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aibig.umk.model.Directory.News;

public interface NewsRepository extends JpaRepository<News, Integer> {
    News findByNewsTitle(String newsTitle);

    Page<News> findByNewsCategoryOrderByNewsDateDesc(String category, Pageable pageable);
}
