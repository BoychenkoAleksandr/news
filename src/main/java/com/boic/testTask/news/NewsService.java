package com.boic.test_task.news;

import com.boic.test_task.common.CrudService;
import com.boic.test_task.common.JpaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "news")
public class NewsService implements CrudService<NewsDtoOut, NewsJpa> {
    private final NewsMapper newsMapper;
    //private final UserRepository userRepository;

    //@Cacheable(key = "#id")
    public NewsDtoOut getNewsById(Long id) {

        return newsMapper.fromJpaEntity(getRepository().findById(id)).orElse(null);
    }

//    @CacheEvict(allEntries = true)
//    public NewsDtoOut createNews(NewsDtoIn request) {
//
//
//        News news = News.builder()
//                .title(request.getTitle())
//                .content(request.getContent())
//                .published(request.isPublished())
//                .createdAt(LocalDateTime.now())
//                .author(author)
//                .build();
//
//        News savedNews = newsRepository.save(news);
//        return convertToDto(savedNews);
//    }
//
//    @CacheEvict(allEntries = true)
//    public NewsDto updateNews(Long id, CreateNewsRequest request, String username) {
//        News news = newsRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("News not found with id: " + id));
//
//        if (!news.getAuthor().getUsername().equals(username)) {
//            throw new AccessDeniedException("You are not the author of this news");
//        }
//
//        news.setTitle(request.getTitle());
//        news.setContent(request.getContent());
//        news.setPublished(request.isPublished());
//        news.setUpdatedAt(LocalDateTime.now());
//
//        News updatedNews = newsRepository.save(news);
//        return convertToDto(updatedNews);
//    }
//
//    private NewsDto convertToDto(News news) {
//        return NewsDto.builder()
//                .id(news.getId())
//                .title(news.getTitle())
//                .content(news.getContent())
//                .published(news.isPublished())
//                .createdAt(news.getCreatedAt())
//                .updatedAt(news.getUpdatedAt())
//                .authorId(news.getAuthor().getId())
//                .build();
//    }

    @Override
    public JpaRepository<NewsJpa, Long> getRepository() {
        return null;
    }

    @Override
    public JpaMapper<NewsDtoOut, NewsJpa> getMapper() {
        return null;
    }
}
