package com.mjc.school.repository.model;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@ToString
public class NewsModel {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;
    private Long authorId;

    public NewsModel(Long id, String title) {
        this.id = id;
        this.title = title;
        this.createdDate = LocalDateTime.now();
        this.lastUpdatedDate = this.createdDate;
    }
    public NewsModel(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = LocalDateTime.now();
        this.lastUpdatedDate = this.createdDate;
    }
    public NewsModel(Long id) {
        this.id = id;
        this.createdDate = LocalDateTime.now();
        this.lastUpdatedDate = this.createdDate;
    }
    public String getCreatedDate(){
        return createdDate.format(DateTimeFormatter.ISO_DATE_TIME);
    }
    public String getLastUpdatedDate(){
        return lastUpdatedDate.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
