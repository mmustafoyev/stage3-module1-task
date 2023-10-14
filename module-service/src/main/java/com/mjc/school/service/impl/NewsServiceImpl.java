package com.mjc.school.service.impl;

import com.mjc.school.dto.NewsDto;
import com.mjc.school.exception.DoubleAdding;
import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.exception.NotNewDataToUpdate;
import com.mjc.school.mapper.NewsMapper;
import com.mjc.school.repository.dataSource.DataSource;
import com.mjc.school.repository.dataAccessObject.impl.NewsImpl;
import com.mjc.school.repository.dataSource.read.NewsRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.NewsService;
import com.mjc.school.validate.NewsValidator;

import java.io.IOException;
import java.util.List;

public class NewsServiceImpl implements NewsService {
    private final DataSource dataSource = new NewsRepository();
    NewsValidator validator = new NewsValidator();
    NewsImpl carry = new NewsImpl();

    @Override
    public NewsDto createNews(String title, String content, String authorId) throws NotExistThisId, IOException {
        NewsModel newsDto = new NewsModel(Long.valueOf(authorId), title, content);
        NewsDto dto = NewsMapper.INSTANCE.toDTO(newsDto);
        validator.validator(dto);
        try {
            carry.create(newsDto);
        } catch (DoubleAdding e) {
            throw new RuntimeException(e);
        }
        return dto;
    }

    @Override
    public List<NewsDto> readAllNews() throws IOException {
        return dataSource.readAll();
    }

    @Override
    public Long readByIdNews(String id){
        Long idl = Long.valueOf(id);
        try {
            carry.readById(idl);
            return idl;
        } catch (NotExistThisId | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public NewsDto updateNews(String id, String title, String content, String authorId) throws NotExistThisId, NotNewDataToUpdate, IOException {
        NewsModel newsDto = new NewsModel(Long.valueOf(authorId));
        newsDto.setTitle(title);
        newsDto.setContent(content);
        carry.update(newsDto);
        NewsDto dto = NewsMapper.INSTANCE.toDTO(newsDto);
        return dto;
    }

    @Override
    public Boolean deleteNews(String id) throws NotExistThisId, IOException {
        return carry.delete(Long.parseLong(id));
    }

    @Override
    public List getAllAuthors() throws IOException {
        return dataSource.readAll();
    }
}
