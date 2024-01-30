package com.aibig.umk.controller.News;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    int pageSize = 1;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/selectedNews")
    public String getSelectedNews(Model model, @RequestParam("newsID") int newsid) {
        News selectedNews = newsService.getNewsById(newsid);

        List<News> tempnews = getAllSortedNewsByDate();
        List<News> top5news = new ArrayList<News>();
        for (int i = 0; i < tempnews.size(); i++) {
            top5news.add(new News(tempnews.get(i)));
            if (i == 2) {
                break;
            }
        }
        Map<String, Long> categoryCounts = newsService.getCategoryCounts();

        model.addAttribute("titlePage", selectedNews.getNewsTitle());
        model.addAttribute("breadcumbs1", "News");
        model.addAttribute("breadcumbs2", selectedNews.getNewsTitle());
        model.addAttribute("newsCat", selectedNews.getNewsCategory());

        model.addAttribute("categoryCounts", categoryCounts);
        model.addAttribute("top5news", top5news);
        model.addAttribute("selectedNews", selectedNews);
        return "News/SelectedNews";
    }

    @GetMapping("/displayImage")
    @ResponseBody
    public ResponseEntity<byte[]> displayImage(@RequestParam("newsId") int newsId) {
        News news = newsService.getNewsById(newsId);

        if (news != null && news.getPrimaryimage() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG

            return new ResponseEntity<>(news.getPrimaryimage(), headers, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/meeting")
    public String showMeeting(Model model, @RequestParam(defaultValue = "0") int page) {

        Page<News> meetingPage = newsService.getSortedNewsByDatePaginated("Meeting", PageRequest.of(page, pageSize));
        List<News> meeting = meetingPage.getContent();
        Map<String, Long> categoryCounts = newsService.getCategoryCounts();

        List<News> tempMeeting = getSortedNewsByDate();
        List<News> top3Meeting = new ArrayList<>();
        for (int i = 0; i < tempMeeting.size(); i++) {
            top3Meeting.add(new News(tempMeeting.get(i)));
            if (i == 2) {
                break;
            }
        }

        model.addAttribute("titlePage", "MEETING ON AIBIG");
        model.addAttribute("breadcumbs1", "News");
        model.addAttribute("breadcumbs2", "MEETING");
        model.addAttribute("categoryCounts", categoryCounts);
        model.addAttribute("top3news", top3Meeting);
        model.addAttribute("news", meeting);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", meetingPage.getTotalPages());

        return "News/Meeting";
    }

    @GetMapping("/news")
    public String showNews(Model model, @RequestParam(defaultValue = "0") int page) {

        Page<News> newsPage = newsService.getSortedNewsByDatePaginated("News", PageRequest.of(page, pageSize));
        List<News> news = newsPage.getContent();
        Map<String, Long> categoryCounts = newsService.getCategoryCounts();

        List<News> tempnews = getSortedNewsByDate();
        List<News> top3news = new ArrayList<News>();
        for (int i = 0; i < tempnews.size(); i++) {
            top3news.add(new News(tempnews.get(i)));
            if (i == 2) {
                break;
            }
        }

        model.addAttribute("titlePage", "NEWS ON AIBIG");
        model.addAttribute("breadcumbs1", "News");
        model.addAttribute("breadcumbs2", "Latest News");
        model.addAttribute("categoryCounts", categoryCounts);
        model.addAttribute("top3news", top3news);
        model.addAttribute("news", news);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", newsPage.getTotalPages());

        return "News/News";
    }

    @GetMapping("/forums")
    public String showForums(Model model, @RequestParam(defaultValue = "0") int page) {

        Page<News> forumsPage = newsService.getSortedNewsByDatePaginated("Forums", PageRequest.of(page, pageSize));
        List<News> forums = forumsPage.getContent();
        Map<String, Long> categoryCounts = newsService.getCategoryCounts();

        List<News> tempForums = getSortedNewsByDate();
        List<News> top3Forums = new ArrayList<>();
        for (int i = 0; i < tempForums.size(); i++) {
            top3Forums.add(new News(tempForums.get(i)));
            if (i == 2) {
                break;
            }
        }

        model.addAttribute("titlePage", "FORUMS ON AIBIG");
        model.addAttribute("breadcumbs1", "News");
        model.addAttribute("breadcumbs2", "FORUMS");
        model.addAttribute("categoryCounts", categoryCounts);
        model.addAttribute("top3news", top3Forums);
        model.addAttribute("news", forums);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", forumsPage.getTotalPages());

        return "News/Forums";
    }

    @GetMapping("/seminar")
    public String showSeminar(Model model, @RequestParam(defaultValue = "0") int page) {

        Page<News> seminarPage = newsService.getSortedNewsByDatePaginated("Seminar", PageRequest.of(page, pageSize));
        List<News> seminar = seminarPage.getContent();
        Map<String, Long> categoryCounts = newsService.getCategoryCounts();

        List<News> tempSeminar = getSortedNewsByDate();
        List<News> top3Seminar = new ArrayList<>();
        for (int i = 0; i < tempSeminar.size(); i++) {
            top3Seminar.add(new News(tempSeminar.get(i)));
            if (i == 2) {
                break;
            }
        }

        model.addAttribute("titlePage", "SEMINAR ON AIBIG");
        model.addAttribute("breadcumbs1", "News");
        model.addAttribute("breadcumbs2", "SEMINAR");
        model.addAttribute("categoryCounts", categoryCounts);
        model.addAttribute("top3news", top3Seminar);
        model.addAttribute("news", seminar);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", seminarPage.getTotalPages());

        return "News/Seminar";
    }

    @GetMapping("/conferences")
    public String showConferences(Model model, @RequestParam(defaultValue = "0") int page) {

        Page<News> conferencesPage = newsService.getSortedNewsByDatePaginated("Conferences",
                PageRequest.of(page, pageSize));
        List<News> conferences = conferencesPage.getContent();
        Map<String, Long> categoryCounts = newsService.getCategoryCounts();

        List<News> tempConferences = getSortedNewsByDate();
        List<News> top3Conferences = new ArrayList<>();
        for (int i = 0; i < tempConferences.size(); i++) {
            top3Conferences.add(new News(tempConferences.get(i)));
            if (i == 2) {
                break;
            }
        }

        model.addAttribute("titlePage", "CONFERENCES ON AIBIG");
        model.addAttribute("breadcumbs1", "News");
        model.addAttribute("breadcumbs2", "CONFERENCES");
        model.addAttribute("categoryCounts", categoryCounts);
        model.addAttribute("top3news", top3Conferences);
        model.addAttribute("news", conferences);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", conferencesPage.getTotalPages());

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

    public List<News> getSortedNewsByDate() {
        List<News> newsList = newsService.getAllNews();

        // Sort the list by date in descending order
        List<News> sortedNews = newsList.stream()
                .sorted(Comparator.comparing(News::getNewsDate).reversed())
                .collect(Collectors.toList());

        return sortedNews;
    }

    public List<News> getAllSortedNewsByDate() {
        List<News> newsList = newsService.getAllNews();
        List<News> sortedNews = newsList.stream().sorted(Comparator.comparing(News::getNewsDate).reversed())
                .collect(Collectors.toList());
        return sortedNews;
    }

}
