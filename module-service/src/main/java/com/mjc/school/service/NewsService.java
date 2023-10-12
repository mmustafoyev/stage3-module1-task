package com.mjc.school.service;

import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.exception.NotNewDataToUpdate;

import java.util.List;

public interface NewsService <T> {

    T createNews(String title, String content, String authorId) throws NotExistThisId;

    List<T> getAllNews();

    T getNewsById(String id);

    T updateNews(String id, String title, String content, String authorId) throws NotExistThisId, NotNewDataToUpdate;
    boolean deleteNews(String Id) throws NotExistThisId;

    List<String> getAllAuthors();
}