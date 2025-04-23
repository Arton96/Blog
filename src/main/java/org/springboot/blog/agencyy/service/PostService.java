package org.springboot.blog.agencyy.service;

import org.springboot.blog.agencyy.dto.PostRequestDto;
import org.springboot.blog.agencyy.dto.PostResponseDto;

import java.util.List;

public interface PostService {
    List<PostResponseDto> getAllPosts();
    PostResponseDto getPostById(Long id);
    PostResponseDto createPost(PostRequestDto postRequestDto);
    PostResponseDto updatePost(Long id, PostRequestDto postDetails);
    void deletePost(Long id);
    List<PostResponseDto> getPostsByCategory(Long categoryId);
    List<PostResponseDto> getPostsByAuthorId(Long authorId);
    List<PostResponseDto> getPostsByTagName(String tagName);

}
