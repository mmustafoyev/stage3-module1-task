package com.mjc.school.controller;

import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.exception.NotNewDataToUpdate;

import java.io.IOException;
import java.util.List;

public interface NewsController<T> {

        T createNews(String title) throws NotExistThisId, IOException;

        List<T> getAllNews() throws IOException;

        T getNewsById(String id);

        T updateNews(String id, String title, String content, String authorId) throws NotNewDataToUpdate, NotExistThisId, IOException;

        Long deleteNews(String Id) throws NotExistThisId, IOException;
    }

