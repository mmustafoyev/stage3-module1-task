package com.mjc.school.repository.dataAccessObject.impl;

import com.mjc.school.exception.DoubleAdding;
import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.exception.NotNewDataToUpdate;
import com.mjc.school.repository.model.NewsModel;

import java.io.IOException;
import java.util.List;

public interface Dao<T> {
    T create(T data) throws DoubleAdding, IOException;
    T readById(Long id) throws NotExistThisId, IOException;
    List<T> readAll() throws IOException;
    NewsModel update(T data) throws NotExistThisId, NotNewDataToUpdate, IOException;
    public Boolean delete(Long id) throws NotExistThisId, IOException;
}
