package org.springboot.blog.agencyy.service;

import org.springboot.blog.agencyy.entity.Category;
import org.springboot.blog.agencyy.entity.Post;
import org.springboot.blog.agencyy.entity.Tag;
import org.springboot.blog.agencyy.entity.User;
import org.springboot.blog.agencyy.repository.CategoryRepository;
import org.springboot.blog.agencyy.repository.PostRepository;
import org.springboot.blog.agencyy.repository.TagRepository;
import org.springboot.blog.agencyy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService{


    private PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;


    @Autowired
    public PostServiceImpl(PostRepository postRepository,UserRepository userRepository, CategoryRepository categoryRepository, TagRepository tagRepository) {
       this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public List<Post> getPostsByCategory(Long categoryId) {
        return postRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Post> getPostsByAuthor(Long authorId) {
        return postRepository.findByAuthorId(authorId);
    }

    @Override
    public List<Post> getPostsByTag(String tagName) {
        return postRepository.findByTagsName(tagName);
    }

    @Override
    public Post createPost(Post post) {

        User author = userRepository.findById(post.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Category category = categoryRepository.findById(post.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Set<Tag> tagsFromDb = new HashSet<>();
        for (Tag tag : post.getTags()) {
            Tag tagFromDb = tagRepository.findById(tag.getId())
                    .orElseThrow(() -> new RuntimeException("Tag not found with ID: " + tag.getId()));
            tagsFromDb.add(tagFromDb);
        }

        // Krijo post-in e ri
        Post p = new Post();
        p.setTitle(post.getTitle());
        p.setContent(post.getContent());
        p.setCreatedAt(LocalDateTime.now());
        p.setUpdatedAt(LocalDateTime.now());
        p.setAuthor(author);
        p.setCategory(category);
        p.setTags(tagsFromDb);
        p.setComments(new ArrayList<>());

        return postRepository.save(p);
    }





//    @Override
//    public Post createPost(Post post) {
//
//
//        Post p = new Post();
//        p.setTitle(post.getTitle());
//        p.setContent(post.getContent());
//        p.setCreatedAt(post.getCreatedAt());
//        p.setUpdatedAt(post.getUpdatedAt());
//        p.setAuthor(post.getAuthor());
//        p.setCategory(post.getCategory());
//        p.setComments(post.getComments());
//        p.setTags(post.getTags());
//        return postRepository.save(post);
//    }

    @Override
    public Post updatePost(Long id, Post postDetails) {
        Post post = getPostById(id);
        if (post != null) {
            post.setTitle(postDetails.getTitle());
            post.setContent(postDetails.getContent());
            post.setCategory(postDetails.getCategory());
            post.setTags(postDetails.getTags());
            return postRepository.save(post);
        }
        return null;    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);

    }
}
