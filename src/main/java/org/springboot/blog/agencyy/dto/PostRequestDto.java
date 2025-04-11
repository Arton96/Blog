package org.springboot.blog.agencyy.dto;


import java.util.List;


public class PostRequestDto {
    private String title;
    private String content;
    private Long categoryId;
    private List<Long>tagIds;
    private Long authorId;

    public PostRequestDto(String title, String content, Long categoryId, List<Long> tagIds, Long authorId) {
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.tagIds = tagIds;
        this.authorId = authorId;
    }
    public PostRequestDto(){

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
