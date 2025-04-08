package org.springboot.blog.agencyy.service;

import org.springboot.blog.agencyy.entity.Tag;
import org.springboot.blog.agencyy.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TagServiceImpl implements TagService{

    private TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

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
