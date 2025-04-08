package org.springboot.blog.agencyy.repository;

import org.springboot.blog.agencyy.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategoryId(Long categoryId);
    List<Post> findByAuthorId(Long authorId);
    List<Post> findByTagsName(String tagName);
}
