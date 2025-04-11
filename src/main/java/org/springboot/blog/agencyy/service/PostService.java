package org.springboot.blog.agencyy.service;

import org.springboot.blog.agencyy.dto.PostRequestDto;
import org.springboot.blog.agencyy.dto.PostResponseDto;
import org.springboot.blog.agencyy.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    PostResponseDto getPostById(Long id);
    List<Post> getPostsByCategory(Long categoryId);
    List<Post> getPostsByAuthor(Long authorId);
    List<Post> getPostsByTag(String tagName);
    PostResponseDto createPost(PostRequestDto postRequestDto);
    Post updatePost(Long id, Post postDetails);
    void deletePost(Long id);
}
