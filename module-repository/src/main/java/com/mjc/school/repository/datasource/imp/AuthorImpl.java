package com.mjc.school.repository.datasource.imp;

import com.mjc.school.exception.DoubleAdding;
import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.reader.AuthorReader;

import java.io.IOException;
import java.util.List;

public class AuthorImpl implements DataSource<Author> {
    private final AuthorReader getAuthor = new AuthorReader();

    @Override
    public Author save(Author data) throws DoubleAdding {
        if(getAll().contains(data)){
            throw new DoubleAdding("This author has");
        }
        data.setId(getAll().size());
        getAll().add(data);
        return data;
    }

    @Override
    public Author getById(Long id) throws NotExistThisId {
        if(getAll().stream().anyMatch(author -> author.getId() != id))
            throw new NotExistThisId("not found id in author");
        return getAll().
                stream().
                filter(author -> author.getId() == id).
                findFirst().
                get();
    }


    @Override
    public List<Author> getAll() {
        return getAuthor.getAuthors();
    }

    @Override
    public void update(Author data) throws NotExistThisId {
        if(getAuthor.getAuthors().stream().anyMatch(author -> author.getId() == data.getId()))
            throw new NotExistThisId("This id is not found");
        getAuthor.getAuthors().
                stream().
                filter(author -> author.getId() == data.getId()).
                forEach(author -> {
                    author.setName(data.getName());
                });
    }



    @Override
    public void delete(Long id) throws NotExistThisId {
        if(getAuthor.getAuthors().stream().anyMatch(author -> author.getId() == id))
            throw new NotExistThisId("This id is not found");
        int idI = Math.toIntExact(id);
        getAuthor.getAuthors().remove(idI);
    }
}
