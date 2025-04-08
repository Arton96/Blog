package org.springboot.blog.agencyy.service;


import org.springboot.blog.agencyy.entity.Tag;

import java.util.List;

public interface TagService {

    List<Tag> getAllTags();

    Tag getTagById(Long id);

    Tag createTag(Tag tag);


    void deleteTag(Long id);
}
