package com.mjc.school.service.impl;

import com.mjc.school.dto.NewsDto;
import com.mjc.school.exception.DoubleAdding;
import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.exception.NotNewDataToUpdate;
import com.mjc.school.mapper.NewsMapper;
import com.mjc.school.repository.DataAccess.impl.NewsRepositoryImpl;
import com.mjc.school.repository.dataSource.Repository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.validate.NewsServiceValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsServiceImpl {
    private final Repository repository;
    private final NewsServiceValidator newsValidator;
    private final NewsRepositoryImpl carry;


    public NewsServiceImpl() {
        repository = new Repository();
        newsValidator = new NewsServiceValidator();
        carry = new NewsRepositoryImpl();
    }


    public NewsDto createNews(NewsDto dto) throws NotExistThisId, IOException {
        Long id = (long)(readAllNews().size() + 1);
        NewsModel newsDto = new NewsModel(dto.getId());
        NewsDto dto2 = NewsMapper.INSTANCE.toDTO(newsDto);
        newsValidator.validator(dto2);
        try {
            carry.create(newsDto);
        } catch (DoubleAdding e) {
            throw new RuntimeException(e);
        }
        return dto2;
    }

    public List<NewsDto> readAllNews() throws IOException {
        List<NewsDto> dto = new ArrayList<>();
        repository.readAll().stream().forEach(newsDto-> dto.add(NewsMapper.INSTANCE.toDTO(newsDto)));
        return dto;
    }
    public NewsDto readByIdNews(Long id){
        try {
            return NewsMapper.INSTANCE.toDTO(carry.readById(id));
        } catch (NotExistThisId | IOException e) {
            throw new RuntimeException(e);
        }

    }


    public NewsDto updateNews(NewsDto dto) throws NotExistThisId, NotNewDataToUpdate, IOException {
        NewsModel newsDto = new NewsModel(dto.getId());
        carry.update(newsDto);
        NewsDto dto2 = NewsMapper.INSTANCE.toDTO(newsDto);
        return dto2;
    }


    public Boolean deleteNews(Long id) throws NotExistThisId, IOException {
        return carry.delete(id);
    }


    public List getAllAuthors() throws IOException {
        return repository.getAuthorsList();
    }
}
