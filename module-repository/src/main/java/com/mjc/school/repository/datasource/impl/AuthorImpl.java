package com.mjc.school.repository.datasource.impl;

import com.mjc.school.exception.DoubleAdding;
import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.reader.AuthorReader;

import java.util.List;

public class AuthorImpl implements DataSource<AuthorModel> {
    private final AuthorReader getAuthor = new AuthorReader();

    @Override
    public AuthorModel create(AuthorModel data) throws DoubleAdding {
        if(readAll().contains(data)){
            throw new DoubleAdding("This author has");
        }
        data.setId(readAll().size());
        readAll().add(data);
        return data;
    }

    @Override
    public AuthorModel readById(Long id) throws NotExistThisId {
        if(readAll().stream().anyMatch(author -> author.getId() != id))
            throw new NotExistThisId("not found id in author");
        return readAll().
                stream().
                filter(author -> author.getId() == id).
                findFirst().
                get();
    }


    @Override
    public List<AuthorModel> readAll() {
        return getAuthor.getAuthors();
    }

    @Override
    public AuthorModel update(AuthorModel data) throws NotExistThisId {
        if(getAuthor.getAuthors().stream().anyMatch(author -> author.getId() == data.getId()))
            throw new NotExistThisId("This id is not found");
        getAuthor.getAuthors().stream().
                filter(author -> author.getId() == data.getId()).
                forEach(author -> {
                    author.setName(data.getName());
                });
        AuthorModel auth =  getAuthor.getAuthors().get((int)data.getId());
        return auth;
    }



    @Override
    public Boolean delete(Long id) throws NotExistThisId {
        if(getAuthor.getAuthors().stream().anyMatch(author -> author.getId() == id))
            throw new NotExistThisId("This id is not found");
        List<AuthorModel> authors = getAuthor.getAuthors();
        int idI = Math.toIntExact(id);
        return authors.remove(authors.get(idI));
    }
}
