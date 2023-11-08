package com.aibig.umk.controller.News;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aibig.umk.model.Directory.News;
import com.aibig.umk.model.User.Internship;
import com.aibig.umk.services.Directory.NewsService;

@RequestMapping("/news")
@Controller
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/displayImage")
    @ResponseBody
    public ResponseEntity<byte[]> displayImage(@RequestParam("newsId") int newsId) {
        News news = newsService.getNewsById(newsId);

        if (news != null && news.getPrimaryimage() != null) {
            System.out.println("Image found");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

            return new ResponseEntity<>(news.getPrimaryimage(), headers, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/meeting")
    public String showMeeting(Model model) {
        List<News> meetings = newsChecking("Meeting");

        model.addAttribute("breadcumbs1", "News");
        model.addAttribute("breadcumbs2", "MEETING");
        model.addAttribute("meeting", meetings);
        return "News/Meeting";
    }

    @GetMapping("/news")
    public String showNews(Model model) {
        List<News> news = newsChecking("News");

        model.addAttribute("breadcumbs1", "News");
        model.addAttribute("breadcumbs2", "Latest News");
        model.addAttribute("news", news);
        return "News/News";
    }

    @GetMapping("/forums")
    public String showForums(Model model) {
        List<News> forums = newsChecking("Forums");

        model.addAttribute("breadcumbs1", "News");
        model.addAttribute("breadcumbs2", "FORUMS");
        model.addAttribute("forums", forums);
        return "News/Forums";
    }

    @GetMapping("/seminar")
    public String showSeminar(Model model) {
        List<News> seminars = newsChecking("Seminar");

        model.addAttribute("breadcumbs1", "News");
        model.addAttribute("breadcumbs2", "SEMINAR");
        model.addAttribute("seminar", seminars);
        return "News/Seminar";
    }

    @GetMapping("/conferences")
    public String showConferences(Model model) {
        List<News> conferences = newsChecking("Conferences");

        model.addAttribute("breadcumbs1", "News");
        model.addAttribute("breadcumbs2", "CONFERENCES");
        model.addAttribute("conferences", conferences);
        return "News/Conferences";
    }

    public List<News> newsChecking(String newsCategory) {
        List<News> tempNews = newsService.getAllNews();
        List<News> tempCat = new ArrayList<>();
        for (News temp : tempNews) {
            if (temp.getNewsCategory().equals(newsCategory)) {
                tempCat.add(temp);
            }
        }
        return tempCat;

    }

}
