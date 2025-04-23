package org.springboot.blog.agencyy.service;


import org.springboot.blog.agencyy.dto.CommentRequestDto;
import org.springboot.blog.agencyy.dto.CommentResponseDto;
import org.springboot.blog.agencyy.entity.Comment;

import java.util.List;

public interface CommentService {

    List<CommentResponseDto> getAllComments();

    List<CommentResponseDto> getCommentsByPost(Long postId);

    List<CommentResponseDto> getCommentsByUser(Long userId);

    List<CommentResponseDto> getCommentsByStatus(String status);

    CommentResponseDto createComment(CommentRequestDto commentRequestDto);

    CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto);

    void deleteComment(Long id);
}
