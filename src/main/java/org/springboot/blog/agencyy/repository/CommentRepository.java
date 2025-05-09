package org.springboot.blog.agencyy.repository;

import org.springboot.blog.agencyy.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
    List<Comment> findByUserId(Long userId);
    List<Comment> findByStatus(String status);
}

