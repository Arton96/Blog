package org.springboot.blog.agencyy.repository;

import org.springboot.blog.agencyy.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByName(String name);
}

