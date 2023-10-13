package com.mjc.school.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewsDto {
    private long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;
    private String authorName;

    public NewsDto() {
    }

    public NewsDto(String title, String content, Long authorId) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }
}
