package com.aibig.umk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aibig.umk.model.Directory.News;
import com.aibig.umk.services.Directory.NewsService;

import lombok.AllArgsConstructor;
import com.aibig.umk.services.Directory.NewsService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class main {

    private final NewsService newsService;

    @RequestMapping("/")
    public String index(Model model) throws Exception {
        List<News> tempnews = newsService.getAllNews();
        List<News> sortedNews = tempnews.stream().sorted(Comparator.comparing(News::getNewsDate).reversed())
                .collect(Collectors.toList());
        model.addAttribute("news", sortedNews);
        return "index";
    }

    @GetMapping("/contact-us")
    public String contactUs(Model model) {
        model.addAttribute("breadcumbs1", "Home");
        model.addAttribute("breadcumbs2", "Contact Us");

        return "Contact/contact-us";
    }
}
