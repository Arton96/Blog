package org.springboot.blog.agencyy.rest;

import org.springboot.blog.agencyy.dto.CommentRequestDto;
import org.springboot.blog.agencyy.dto.CommentResponseDto;
import org.springboot.blog.agencyy.entity.Comment;
import org.springboot.blog.agencyy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {


    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentResponseDto> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/post/{postId}")
    public List<CommentResponseDto> getCommentsByPost(@PathVariable Long postId) {
        return commentService.getCommentsByPost(postId);
    }

    @GetMapping("/user/{userId}")
    public List<CommentResponseDto> getCommentsByUser(@PathVariable Long userId) {
        return commentService.getCommentsByUser(userId);
    }

    @GetMapping("/status/{status}")
    public List<CommentResponseDto> getCommentsByStatus(@PathVariable String status) {
        return commentService.getCommentsByStatus(status);
    }

    @PostMapping("/create")
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto createdComment = commentService.createComment(commentRequestDto);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto updatedComment = commentService.updateComment(id, commentRequestDto);
        return updatedComment != null ? ResponseEntity.ok(updatedComment) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
