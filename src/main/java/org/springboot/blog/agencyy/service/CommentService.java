package org.springboot.blog.agencyy.service;


import org.springboot.blog.agencyy.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllComments();

    List<Comment> getCommentsByPost(Long postId);

    List<Comment> getCommentsByUser(Long userId);

    List<Comment> getCommentsByStatus(String status);

    Comment createComment(Comment comment);

    Comment updateComment(Long id, Comment commentDetails);

    void deleteComment(Long id);
}
