package com.mjc.school.repository.datasource;



import com.mjc.school.exception.DoubleAdding;
import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.exception.NotNewDataToUpdate;

import java.util.List;

public interface DataSource<T> {
    Object create(T data) throws DoubleAdding;
    Object readById(Long id) throws NotExistThisId;
    List<T> readAll();
    Object update(T data) throws NotExistThisId, NotNewDataToUpdate;
    Boolean delete(Long id) throws NotExistThisId;

}
