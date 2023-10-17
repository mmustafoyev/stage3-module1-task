package com.mjc.school.controller.impl;

import com.mjc.school.dto.NewsDto;
import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.exception.NotNewDataToUpdate;
import com.mjc.school.service.impl.NewsServiceImpl;
import com.mjc.school.validate.NewsServiceValidator;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class NewsControllerImpl {
    private final NewsServiceImpl service = new NewsServiceImpl();


    public NewsDto createNews(NewsDto dto) throws NotExistThisId, IOException {
        NewsServiceValidator validator = new NewsServiceValidator();
        NewsDto newsDTO = service.createNews(dto);
        newsDTO.setCreateDate(LocalDateTime.now());
        newsDTO.setLastUpdateDate(LocalDateTime.now());
        newsDTO.setId(getAllNews().size() + 1);
        validator.validator(newsDTO);

        return newsDTO;
    }

    public List<NewsDto> getAllNews() throws IOException {
        return service.readAllNews();
    }


    public Long getNewsById(String id) {
        return service.readByIdNews(id);
    }

    public NewsDto updateNews(NewsDto newsDto) throws NotNewDataToUpdate, NotExistThisId, IOException {
        return service.updateNews(newsDto);
    }

    public Long deleteNews(String id) throws NotExistThisId, IOException {
        service.deleteNews(String.valueOf(Long.valueOf(id)));
        return Long.valueOf(id);
    }
}
