package com.mjc.school.repository.dataSource;

import com.mjc.school.repository.dataSource.AuthorReader;
import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.NewsModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
public class NewsRepository {
    private final List<NewsModel> data = new ArrayList<>();

    private AuthorReader author = new AuthorReader();

    public List<NewsModel> getNews() {
        return data;
    }

    public List<NewsModel> readByTitles() throws IOException {

        BufferedReader in = null;
        InputStreamReader isr = null;
        List<Author> listOfAuthors;
        try {
            isr = new InputStreamReader(Files.newInputStream(new File("module-repository/src/main/resources/news.txt").toPath()));
            in = new BufferedReader(isr);
            String line;
            Long id = 0l;
            while ((line = in.readLine()) != null){
                id++;
                String title = line;
                NewsModel news = new NewsModel(id,line);
                data.add(news);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(in != null)
                in.close();
            if(isr != null)
                in.close();
        }
        return data;
    }
    private List<NewsModel> readByContext() throws IOException {
        int index = 0;
        String line;
        BufferedReader in = null;
        InputStreamReader isr = null;
        List<Author> listOfAuthors;
        try{
            isr = new InputStreamReader(Files.newInputStream(new File("module-repository/src/main/resources/context.txt").toPath()));
            in = new BufferedReader(isr);
            while ((line = in.readLine()) != null) {
                if (index < data.size()) {
                    data.get(index).setContent(line);
                    index++;
                }
            }
        } catch (IOException e) {
            System.out.println("Reading context.txt error");
        }
        finally {
            if(in != null)
                in.close();
            if(isr != null)
                isr.close();
        }
        return data;
    }


    public List<NewsModel> getNewsList() throws IOException {
        return readAll();
    }



    public List<NewsModel> readAll() throws IOException {
        readByTitles();
        readByContext();
        author.readAuthorsFromFile();
        author.getAuthors();
        return data;
    }

    public List<Author> getAuthorsList() throws IOException {
        author.readAuthorsFromFile();
        return author.getAuthors();
    }
}
