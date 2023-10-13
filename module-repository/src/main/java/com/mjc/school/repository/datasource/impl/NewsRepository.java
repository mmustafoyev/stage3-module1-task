package com.mjc.school.repository.datasource.impl;

import com.mjc.school.exception.DoubleAdding;
import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.exception.NotNewDataToUpdate;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.reader.NewsReader;

import java.time.LocalDateTime;
import java.util.List;

public class NewsRepository implements DataSource<NewsModel> {
    private  DataSource dataSource;
    private NewsReader reader = new NewsReader();


    public NewsRepository() {

    }


    @Override
    public Object create(NewsModel data) throws DoubleAdding {
        if(readAll().contains(data)){
            throw new DoubleAdding("there is news model");
        }
        data.setId((long) readAll().size());
        readAll().add(data);
        return data;
    }

    @Override
    public Object readById(Long id) throws NotExistThisId {
        if(readAll().stream().anyMatch(news -> news.getId() != id))
            throw new NotExistThisId("not found id in news");
        return readAll().
                stream().
                filter(news -> news.getId() == id).
                findFirst().
                get();
    }

    @Override
    public List<NewsModel> readAll() {
        return reader.getNews();
    }

    @Override
    public Object update(NewsModel data) throws NotExistThisId, NotNewDataToUpdate {
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
    public Boolean delete(Long id) throws NotExistThisId {
        if(readAll().stream().anyMatch(news -> news.getId() != id))
            throw new NotExistThisId("not found id in news");
        int id_ = Math.toIntExact(id);
        return readAll().remove(readById(id));
    }


}
