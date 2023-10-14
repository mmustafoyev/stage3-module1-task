package com.mjc.school.repository.dataSource;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.NewsModel;

import java.io.IOException;
import java.util.List;

public interface DataSource<T> {
    List<NewsModel> getNewsList() throws IOException;
    List<NewsModel> readAll() throws IOException;
    List<Author> getAuthorsList() throws IOException;
}
