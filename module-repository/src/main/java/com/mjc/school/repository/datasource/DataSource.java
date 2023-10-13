package com.mjc.school.repository.datasource;



import com.mjc.school.exception.DoubleAdding;
import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.exception.NotNewDataToUpdate;

import java.util.List;

public interface DataSource<T> {
    T create(T data) throws DoubleAdding;
    T readById(Long id) throws NotExistThisId;
    List<T> readAll();
    T update(T data) throws NotExistThisId, NotNewDataToUpdate;
    boolean delete(Long id) throws NotExistThisId;

}
