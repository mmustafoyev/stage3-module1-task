package com.mjc.school.service.impl;

import com.mjc.school.dto.NewsDto;
import com.mjc.school.exception.DoubleAdding;
import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.exception.NotNewDataToUpdate;
//import com.mjc.school.mapping.NewsMapper;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.datasource.impl.AuthorImpl;
import com.mjc.school.repository.datasource.impl.NewsImpl;
import com.mjc.school.repository.datasource.impl.NewsRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.NewsService;
import com.mjc.school.validate.Validator;

import java.time.LocalDateTime;
import java.util.List;

public class NewsServiceImpl implements NewsService {
//    private final NewsMapper newsMapper = NewsMapper.INSTANCE;
    private final DataSource dataSource1 = new AuthorImpl();
    private DataSource dataSource = new NewsRepository(dataSource1);

    private  DataSource dataSource2 = new NewsImpl();
    Validator validator = new Validator();


//    // Example method for mapping NewsModel to NewsDto
//    public NewsDto mapNewsToDTO(NewsModel newsModel, AuthorModel author) {
//        return newsMapper.newsToNewsDTO(newsModel, author);
//    }

    @Override
    public NewsDto createNews(String title, String content, String authorId) throws NotExistThisId {
        NewsDto newsDto = new NewsDto();
        newsDto.setTitle(title);
        newsDto.setContent(content);
        newsDto.setAuthorId(Long.valueOf(authorId));
        newsDto.setCreateDate(LocalDateTime.now());
        AuthorModel author = (AuthorModel) dataSource1.readById(Long.parseLong(authorId));
        newsDto.setAuthorName(author.getName());
        validator.validator(newsDto);
        try {
            dataSource2.create(newsDto);
        } catch (DoubleAdding e) {
            throw new RuntimeException(e);
        }
        return newsDto;
    }

    @Override
    public List<NewsDto> readAllNews() {
        return dataSource2.readAll();
    }

    @Override
    public Long readByIdNews(String id){
        Long idl = Long.valueOf(id);
        try {
            dataSource2.readById(idl);
            return idl;
        } catch (NotExistThisId e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public NewsDto updateNews(String id, String title, String content, String authorId) throws NotExistThisId, NotNewDataToUpdate {
        NewsDto newsDto = new NewsDto();
        newsDto.setTitle(title);
        newsDto.setContent(content);
        newsDto.setAuthorId(Long.valueOf(authorId));
        newsDto.setId(Long.parseLong(id));
        newsDto.setCreateDate(readAllNews().get(Integer.parseInt(id)).getCreateDate());
        newsDto.setLastUpdateDate(LocalDateTime.now());
        AuthorModel author = (AuthorModel) dataSource1.readById(Long.parseLong(authorId));
        newsDto.setAuthorName(author.getName());
        dataSource2.update(newsDto);
        return newsDto;
    }

    @Override
    public Boolean deleteNews(String id) throws NotExistThisId {
        return dataSource2.delete(Long.parseLong(id));
    }

    @Override
    public List getAllAuthors() {
        return dataSource1.readAll();
    }
}
