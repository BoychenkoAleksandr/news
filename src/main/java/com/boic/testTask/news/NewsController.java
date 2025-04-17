package com.boic.test_task.news;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping("/{id}")
    public ResponseEntity<NewsDtoOut> getNewsById(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.getNewsById(id));
    }

    @PostMapping
    //@PreAuthorize("hasRole('EDITOR') or hasRole('ADMIN')")
    public ResponseEntity<NewsDtoOut> createNews(
            @RequestBody NewsDtoOut news) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newsService.persist(news));
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('EDITOR') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteNews(
            @PathVariable Long id) {
        newsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
