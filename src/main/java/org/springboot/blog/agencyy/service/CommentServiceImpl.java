package org.springboot.blog.agencyy.service;

import org.springboot.blog.agencyy.dto.CommentRequestDto;
import org.springboot.blog.agencyy.dto.CommentResponseDto;
import org.springboot.blog.agencyy.entity.Comment;
import org.springboot.blog.agencyy.entity.Post;
import org.springboot.blog.agencyy.entity.User;
import org.springboot.blog.agencyy.repository.CommentRepository;
import org.springboot.blog.agencyy.repository.PostRepository;
import org.springboot.blog.agencyy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{



    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository,UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<CommentResponseDto> getAllComments() {
        List<Comment>comments = commentRepository.findAll();
        List<CommentResponseDto> crs = new ArrayList<>();
        for(Comment c : comments){
            CommentResponseDto cr = new CommentResponseDto();
            cr.setId(c.getId());
            cr.setContent(c.getContent());
            cr.setUsername(c.getUser().getUsername());
            cr.setPostId(c.getPost().getId());
            cr.setCreatedAt(c.getCreatedAt());

            crs.add(cr);
        }
        return crs;
    }

    @Override
    public List<CommentResponseDto> getCommentsByPost(Long postId) {
        List<Comment> commentsById = commentRepository.findByPostId(postId);
        List<CommentResponseDto> crs = new ArrayList<>();

        for (Comment c : commentsById) {
            CommentResponseDto cr = new CommentResponseDto();
            cr.setId(c.getId());
            cr.setContent(c.getContent());
            cr.setUsername(c.getUser().getUsername());
            cr.setPostId(c.getPost().getId());
            cr.setCreatedAt(c.getCreatedAt());

            crs.add(cr);
        }

        return crs;
    }

    @Override
    public List<CommentResponseDto> getCommentsByUser(Long userId) {
        List<Comment> commentsByUserId = commentRepository.findByUserId(userId);
        List<CommentResponseDto> crs = new ArrayList<>();

        for (Comment c : commentsByUserId) {
            CommentResponseDto cr = new CommentResponseDto();
            cr.setId(c.getId());
            cr.setContent(c.getContent());
            cr.setUsername(c.getUser().getUsername());
            cr.setPostId(c.getPost().getId());
            cr.setCreatedAt(c.getCreatedAt());

            crs.add(cr);
        }

        return crs;
    }

    @Override
    public List<CommentResponseDto> getCommentsByStatus(String status) {
        List<Comment> commentsByStatus = commentRepository.findByStatus(status);
        List<CommentResponseDto> crs = new ArrayList<>();

        for (Comment c : commentsByStatus) {
            CommentResponseDto cr = new CommentResponseDto();
            cr.setId(c.getId());
            cr.setContent(c.getContent());
            cr.setUsername(c.getUser().getUsername());
            cr.setPostId(c.getPost().getId());
            cr.setCreatedAt(c.getCreatedAt());

            crs.add(cr);
        }

        return crs;    }

    @Override
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto) {
        Post post = postRepository.findById(commentRequestDto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        User user = userRepository.findById(commentRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setContent(commentRequestDto.getContent());
        comment.setStatus(commentRequestDto.getStatus() != null ? commentRequestDto.getStatus() : "pending");
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        CommentResponseDto responseDto = new CommentResponseDto();
        responseDto.setId(savedComment.getId());
        responseDto.setContent(savedComment.getText());
        responseDto.setUsername(user.getUsername());
        responseDto.setPostId(post.getId());
        responseDto.setCreatedAt(savedComment.getCreatedAt());

        return responseDto;    }


    @Override
    public CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment existingComment = optionalComment.get();
            existingComment.setContent(commentRequestDto.getContent());
            existingComment.setStatus(commentRequestDto.getStatus());

            Comment updatedComment = commentRepository.save(existingComment);

            CommentResponseDto responseDto = new CommentResponseDto();
            responseDto.setId(updatedComment.getId());
            responseDto.setContent(updatedComment.getText()); // ose .getContent()
            responseDto.setUsername(updatedComment.getUser().getUsername());
            responseDto.setPostId(updatedComment.getPost().getId());
            responseDto.setCreatedAt(updatedComment.getCreatedAt());

            return responseDto;
        }
        return null;
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);

    }
}
