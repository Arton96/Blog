package org.springboot.blog.agencyy.rest;

import org.springboot.blog.agencyy.dto.PostRequestDto;
import org.springboot.blog.agencyy.dto.PostResponseDto;
import org.springboot.blog.agencyy.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {


    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponseDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id) {
        PostResponseDto post = postService.getPostById(id);
        return post != null ? ResponseEntity.ok(post) : ResponseEntity.notFound().build();
    }

    @GetMapping("/category/{categoryId}")
    public List<PostResponseDto> getPostsByCategory(@PathVariable Long categoryId) {
        return postService.getPostsByCategory(categoryId);
    }

    @GetMapping("/author/{authorId}")
    public List<PostResponseDto> getPostsByAuthor(@PathVariable Long authorId) {
        return postService.getPostsByAuthorId(authorId);
    }

    @GetMapping("/tag/{tagName}")
    public List<PostResponseDto> getPostsByTag(@PathVariable String tagName) {
        return postService.getPostsByTagName(tagName);
    }

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postRequestDto) {
        PostResponseDto createdPost = postService.createPost(postRequestDto);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        PostResponseDto updatedPost = postService.updatePost(id, postRequestDto);
        return updatedPost != null ? ResponseEntity.ok(updatedPost) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
