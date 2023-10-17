package com.mjc.school.repository.DataAccess.impl;

import com.mjc.school.exception.DoubleAdding;
import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.exception.NotNewDataToUpdate;
import com.mjc.school.repository.DataAccess.Dao;
import com.mjc.school.repository.dataSource.NewsRepository;
import com.mjc.school.repository.model.NewsModel;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class NewsRepositoryImpl implements Dao<NewsModel> {

    private final NewsRepository dataSource = new NewsRepository();

    @Override
    public NewsModel create(NewsModel data) throws DoubleAdding, IOException {
        if(readAll().contains(data)){
            throw new DoubleAdding("there is news model");
        }
        data.setId((long) readAll().size());
        readAll().add(data);
        return data;
    }

    @Override
    public NewsModel readById(Long id) throws NotExistThisId, IOException {
        if(readAll().stream().anyMatch(news -> news.getId() != id))
            throw new NotExistThisId("not found id in news");
        return readAll().
                stream().
                filter(news -> news.getId() == id).
                findFirst().
                get();
    }

    @Override
    public List<NewsModel> readAll() throws IOException {
        return dataSource.getNewsList();
    }

    @Override
    public NewsModel update(NewsModel data) throws NotExistThisId, NotNewDataToUpdate, IOException {
        if(readAll().contains(data)) {
            throw new NotNewDataToUpdate("This news not new to update");
        }
        int id = Math.toIntExact(data.getId());
        NewsModel newsModel = readAll().get(id);
        newsModel.setContent(data.getContent());
        newsModel.setTitle(data.getTitle());
        newsModel.setLastUpdatedDate(LocalDateTime.now());
        newsModel.setAuthorId(data.getAuthorId());
        return newsModel;
    }

    @Override
    public Boolean delete(Long id) throws NotExistThisId, IOException {
        if(readAll().stream().anyMatch(news -> news.getId() != id))
            throw new NotExistThisId("not found id in news");
        int id_ = Math.toIntExact(id);
        return readAll().remove(readById(id));
    }


}
