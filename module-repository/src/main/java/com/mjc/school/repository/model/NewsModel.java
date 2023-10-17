package com.mjc.school.repository.model;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@ToString
public class NewsModel {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;

    public NewsModel(Long id, String title) {
        this.id = id;
        this.title = title;
        this.createDate = LocalDateTime.now();
        this.lastUpdateDate = this.createDate;
    }
    public NewsModel(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = LocalDateTime.now();
        this.lastUpdateDate = this.createDate;
    }
    public NewsModel(Long id) {
        this.id = id;
        this.createDate = LocalDateTime.now();
        this.lastUpdateDate = this.createDate;
    }
    public String getCreateDate(){
        return createDate.format(DateTimeFormatter.ISO_DATE_TIME);
    }
    public String getLastUpdateDate(){
        return lastUpdateDate.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
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

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
