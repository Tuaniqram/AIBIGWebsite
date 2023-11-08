package com.aibig.umk.services.Directory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aibig.umk.repository.Directory.NewsRepository;
import com.aibig.umk.model.Directory.News;

import java.util.List;

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

}
