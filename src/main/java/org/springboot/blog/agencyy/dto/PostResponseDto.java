package org.springboot.blog.agencyy.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String category;
    private String authorName;
    private List<String>tags;
    private LocalDateTime createdAt;

    public PostResponseDto(){

    }

    public PostResponseDto(Long id, String title, String content, String category, String authorName, List<String> tags, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.authorName = authorName;
        this.tags = tags;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
