package com.mjc.school.service;

import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.exception.NotNewDataToUpdate;

import java.io.IOException;
import java.util.List;

public interface NewsService <T> {

    T createNews(String title, String content, String authorId) throws NotExistThisId, IOException;

    List<T> readAllNews() throws IOException;

    T readByIdNews(String id);

    T updateNews(String id, String title, String content, String authorId) throws NotExistThisId, NotNewDataToUpdate, IOException;
    Boolean deleteNews(String Id) throws NotExistThisId, IOException;

    List<String> getAllAuthors() throws IOException;
}