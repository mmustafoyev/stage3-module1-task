package com.mjc.school.controller.impl;

import com.mjc.school.controller.NewsController;
import com.mjc.school.dto.NewsDto;
import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.exception.NotNewDataToUpdate;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.impl.NewsServiceImpl;
import com.mjc.school.validate.NewsServiceValidatorImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class NewsControllerImpl implements NewsController<NewsDto> {
    NewsService<NewsDto> service = new NewsServiceImpl();

    @Override
    public NewsDto createNews(String title) throws NotExistThisId, IOException {
        NewsServiceValidatorImpl validator = new NewsServiceValidatorImpl();
        NewsDto newsDTO = service.createNews(title);
        newsDTO.setCreateDate(LocalDateTime.now());
        newsDTO.setLastUpdateDate(LocalDateTime.now());
        newsDTO.setId(getAllNews().size() + 1);
        validator.validator(newsDTO);

        return newsDTO;
    }

    @Override
    public List<NewsDto> getAllNews() throws IOException {
        return service.readAllNews();
    }

    @Override
    public NewsDto getNewsById(String id) {
        return service.readByIdNews(id);
    }

    @Override
    public NewsDto updateNews(String id, String title, String content, String authorId) throws NotNewDataToUpdate, NotExistThisId, IOException {
        return service.updateNews(id);
    }

    @Override
    public Long deleteNews(String id) throws NotExistThisId, IOException {
        service.deleteNews(String.valueOf(Long.valueOf(id)));
        return Long.valueOf(id);
    }
}
