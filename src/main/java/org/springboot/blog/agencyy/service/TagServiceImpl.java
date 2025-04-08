package org.springboot.blog.agencyy.service;

import org.springboot.blog.agencyy.entity.Tag;
import org.springboot.blog.agencyy.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TagServiceImpl implements TagService{

    @Autowired
    private TagRepository tagRepository;
    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getTagById(Long id) {
        return tagRepository.findById(id).orElse(null);
    }

    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }


    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);

    }
}
