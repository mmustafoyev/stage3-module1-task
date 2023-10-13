package com.mjc.school.repository.reader;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
public class NewsReader {
    private final List<NewsModel> data = new ArrayList<>();
    private static NewsReader INSTANCE;

    public static NewsReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NewsReader();
        }
        return INSTANCE;
    }

    public List<NewsModel> getNews() {
        return data;
    }

    public void getTitles() throws IOException {

        BufferedReader in = null;
        InputStreamReader isr = null;
        List<AuthorModel> listOfAuthors;
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
    }
    private void readByContext() throws IOException {
        int index = 0;
        String line;
        BufferedReader in = null;
        InputStreamReader isr = null;
        List<AuthorModel> listOfAuthors;
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
    }

}
