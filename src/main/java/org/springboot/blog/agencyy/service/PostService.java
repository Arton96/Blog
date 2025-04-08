package org.springboot.blog.agencyy.service;

import org.springboot.blog.agencyy.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(Long id);
    List<Post> getPostsByCategory(Long categoryId);
    List<Post> getPostsByAuthor(Long authorId);
    List<Post> getPostsByTag(String tagName);
    Post createPost(Post post);
    Post updatePost(Long id, Post postDetails);
    void deletePost(Long id);
}
