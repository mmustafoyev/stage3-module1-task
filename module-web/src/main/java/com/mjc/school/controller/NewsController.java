package com.mjc.school.controller;

import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.exception.NotNewDataToUpdate;

import java.util.List;

public interface NewsController<T> {

        T createNews(String title, String content, String authorId) throws NotExistThisId;

        List<T> getAllNews();

        T getNewsById(String id);

        T updateNews(String id, String title, String content, String authorId) throws NotNewDataToUpdate, NotExistThisId;

        Boolean deleteNews(String Id) throws NotExistThisId;
    }

