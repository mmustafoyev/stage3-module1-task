package com.mjc.school.dto;

import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class NewsDTO {
    private long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;
    private String authorName;

    public NewsDTO() {
    }

    public NewsDTO( String title, String content, Long authorId) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }
}
