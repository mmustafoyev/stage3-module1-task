package com.mjc.school.service.impl;

import com.mjc.school.dto.NewsDto;
import com.mjc.school.exception.DoubleAdding;
import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.exception.NotNewDataToUpdate;
import com.mjc.school.mapper.NewsMapper;
import com.mjc.school.repository.dataSource.DataSource;
import com.mjc.school.repository.DataAccess.impl.NewsImpl;
import com.mjc.school.repository.dataSource.read.NewsRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.NewsService;
import com.mjc.school.validate.NewsServiceValidatorImpl;

import java.io.IOException;
import java.util.List;

public class NewsServiceImpl implements NewsService {
    private final DataSource dataSource = new NewsRepository();
    NewsServiceValidatorImpl validator = new NewsServiceValidatorImpl();
    NewsImpl carry = new NewsImpl();

    @Override
    public NewsDto createNews(String title) throws NotExistThisId, IOException {
        Long id = (long)(readAllNews().size() + 1);
        NewsModel newsDto = new NewsModel(id, title);
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
    public NewsDto readByIdNews(String id){
        Long idl = Long.valueOf(id);
        try {
            return NewsMapper.INSTANCE.toDTO(carry.readById(idl));
        } catch (NotExistThisId | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public NewsDto updateNews(String id) throws NotExistThisId, NotNewDataToUpdate, IOException {
        NewsModel newsDto = new NewsModel(Long.valueOf(id));
        carry.update(newsDto);
        NewsDto dto = NewsMapper.INSTANCE.toDTO(newsDto);
        return dto;
    }

    @Override
    public Long deleteNews(String id) throws NotExistThisId, IOException {
        return Long.valueOf(id);
    }

    @Override
    public List getAllAuthors() throws IOException {
        return dataSource.readAll();
    }
}
