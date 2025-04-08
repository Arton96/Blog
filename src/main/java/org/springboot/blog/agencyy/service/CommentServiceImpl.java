package org.springboot.blog.agencyy.service;

import org.springboot.blog.agencyy.entity.Comment;
import org.springboot.blog.agencyy.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CommentServiceImpl implements CommentService{



    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public List<Comment> getCommentsByUser(Long userId) {
        return commentRepository.findByUserId(userId);
    }

    @Override
    public List<Comment> getCommentsByStatus(String status) {
        return commentRepository.findByStatus(status);
    }

    @Override
    public Comment createComment(Comment comment) {
        return null;
    }


    @Override
    public Comment updateComment(Long id, Comment commentDetails) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment existingComment = optionalComment.get();
            existingComment.setText(commentDetails.getText());
            existingComment.setStatus(commentDetails.getStatus());
            return commentRepository.save(existingComment);
        }
        return null;     }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);

    }
}
