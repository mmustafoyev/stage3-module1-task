package com.mjc.school.repository.datasource;



import com.mjc.school.exception.DoubleAdding;
import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.exception.NotNewDataToUpdate;

import java.util.List;

public interface DataSource<T> {
    T save(T data) throws DoubleAdding;
    T getById(Long id) throws NotExistThisId;
    List<T> getAll();
    void update(T data) throws NotExistThisId, NotNewDataToUpdate;
    void delete(Long id) throws NotExistThisId;

}
