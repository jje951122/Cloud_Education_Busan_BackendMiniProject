package com.project.nmt.controller;

import com.project.nmt.model.Article;
import com.project.nmt.service.ArticleService;
import com.project.nmt.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final StockService stockService;
    private final ArticleService articleService;

    @GetMapping("/article")
    public String getArticleMain(Model model) {
        List<String> keywords = stockService.getStockList();
        List<Article> articles = articleService.getAllArticles();

        model.addAttribute("keywordList", keywords);
        model.addAttribute("articleList", articles);

        return "article/article-main";
    }

    @GetMapping("/article/list/{keyword}")
    public String getArticleList(@PathVariable("keyword") String keyword, Model model) {
        List<Article> articles = articleService.getArticlesByKeyword(keyword);

        model.addAttribute("articleList", articles);

        return "article/article-list";
    }

}
