package org.springboot.blog.agencyy.dto;

public class CommentRequestDto {
    private String content;
    private Long postId;
    private Long userId;
    private String status;

    public CommentRequestDto(String content, Long postId, Long userId,String status) {
        this.content = content;
        this.postId = postId;
        this.userId = userId;
        this.status = status;
    }

    public CommentRequestDto(){

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
