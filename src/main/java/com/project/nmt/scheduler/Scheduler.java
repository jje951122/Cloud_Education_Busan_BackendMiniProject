package com.project.nmt.scheduler;

import com.project.nmt.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Scheduler {

    private final ArticleService articleService;

    @Scheduled(cron = "0 0 14 * * *")
    public void articleUpdate() {
        articleService.scrapAll();
    }

}
