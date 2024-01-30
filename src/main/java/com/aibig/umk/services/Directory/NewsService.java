package com.aibig.umk.services.Directory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aibig.umk.repository.Directory.NewsRepository;
import com.aibig.umk.model.Directory.News;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NewsService {
    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public void saveNews(News news) {
        newsRepository.save(news);
    }

    public News getNewsById(int newsId) {
        return newsRepository.findById(newsId).orElse(null);
    }

    public News getNewsByName(String newsName) {
        return newsRepository.findByNewsTitle(newsName);
    }

    public void deleteNewsById(int newsId) {
        newsRepository.deleteById(newsId);
    }

    public void deleteAllNews() {
        newsRepository.deleteAll();
    }

    public void updateNews(News news) {
        News existingNews = new News(news);
        newsRepository.save(existingNews);
    }

    public Map<String, Long> getCategoryCounts() {
        List<News> allNews = getAllNews();

        // Group news by category and count occurrences
        return allNews.stream()
                .collect(Collectors.groupingBy(News::getNewsCategory, Collectors.counting()));
    }

    public Page<News> getSortedNewsByDatePaginated(String category, Pageable pageable) {
        // Implement your logic to fetch news by category and sort by date
        // Use your repository method that returns Page<News>
        return newsRepository.findByNewsCategoryOrderByNewsDateDesc(category, pageable);
    }

}
