package com.mjc.school.repository.datasource.imp;

import com.mjc.school.exception.DoubleAdding;
import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.exception.NotNewDataToUpdate;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.reader.NewsReader;

import java.time.LocalDateTime;
import java.util.List;

public class NewsImpl implements DataSource<NewsModel> {
    private NewsReader reader = new NewsReader();
    @Override
    public NewsModel save(NewsModel data) throws DoubleAdding {
        if(getAll().contains(data)){
            throw new DoubleAdding("there is news model");
        }
        data.setId((long) getAll().size());
        getAll().add(data);
        return data;
    }

    @Override
    public NewsModel getById(Long id) throws NotExistThisId {
        if(getAll().stream().anyMatch(news -> news.getId() != id))
            throw new NotExistThisId("not found id in news");
        return getAll().
                stream().
                filter(news -> news.getId() == id).
                findFirst().
                get();
    }

    @Override
    public List<NewsModel> getAll() {
        return reader.getNews();
    }

    @Override
    public void update(NewsModel data) throws NotExistThisId, NotNewDataToUpdate {
        if(getAll().contains(data))
            throw new NotNewDataToUpdate("This news not new to update");
        int id = Math.toIntExact(data.getId());
        getAll().get(id).setContent(data.getContent());
        getAll().get(id).setTitle(data.getTitle());
        getAll().get(id).setLastUpdatedDate(LocalDateTime.now());
        getAll().get(id).setAuthorId(data.getAuthorId());
    }

    @Override
    public void delete(Long id) throws NotExistThisId {
        if(getAll().stream().anyMatch(news -> news.getId() != id))
            throw new NotExistThisId("not found id in news");
        getAll().remove(id);
    }


}
