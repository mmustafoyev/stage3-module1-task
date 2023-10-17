package com.mjc.school.repository.dataSource;

import com.mjc.school.repository.model.Author;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class AuthorReader {
    public static AuthorReader INSTANCE;

    private static AuthorReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AuthorReader();
        }
        return INSTANCE;
    }
    private List<Author> listOfAuthors = new ArrayList<>();

    public List<Author> getAuthors() {
        return listOfAuthors;
    }
    public void readAuthorsFromFile() throws IOException {
        BufferedReader in = null;
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(Files.newInputStream(new File("module-repository/src/main/resources/author.txt").toPath()));
            in = new BufferedReader(isr);
            String line;
            Long id = 1l;
            while ((line = in.readLine()) != null){
                Author author = new Author(line, id);
                listOfAuthors.add(author);
                id++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(isr != null)
                isr.close();
            if(in != null)
                in.close();
        }
    }


}
