package com.mjc.school.controller.impl;

import com.mjc.school.controller.NewsController;
import com.mjc.school.dto.NewsDTO;
import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.exception.NotNewDataToUpdate;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.impl.NewsServiceImpl;
import com.mjc.school.validate.Validator;

import java.util.List;

public class NewsControllerImpl implements NewsController<NewsDTO> {
    NewsService<NewsDTO> service = new NewsServiceImpl();

    @Override
    public NewsDTO createNews(String title, String content, String authorId) throws NotExistThisId {
        Validator validator = new Validator();
        NewsDTO newsDTO = service.createNews(title,content, authorId);
        validator.validator(newsDTO);
        return newsDTO;
    }

    @Override
    public List<NewsDTO> getAllNews() {
        return service.getAllNews();
    }

    @Override
    public NewsDTO getNewsById(String id) {
        return service.getNewsById(id);
    }

    @Override
    public NewsDTO updateNews(String id, String title, String content, String authorId) throws NotNewDataToUpdate, NotExistThisId {
        return service.updateNews(id,title, content, authorId);
    }

    @Override
    public Boolean deleteNews(String id) throws NotExistThisId {
        return service.deleteNews(id);
    }
}
