package org.springboot.blog.agencyy.service;

import org.springboot.blog.agencyy.dto.PostRequestDto;
import org.springboot.blog.agencyy.dto.PostResponseDto;
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
import java.util.*;

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
    public PostResponseDto getPostById(Long id) {
        Post postById= postRepository.findById(id).orElse(null);
        PostResponseDto pr = new PostResponseDto();
        if(postById != null){

            pr.setId(postById.getId());
            pr.setAuthorName(postById.getAuthor().getUsername());
            pr.setCategory(postById.getCategory().getName());
            pr.setContent(postById.getContent());

            List<String>tagNames = new ArrayList<>();
            for(Tag t: postById.getTags()){
                tagNames.add(t.getName());
            }
            pr.setTags(tagNames);
            pr.setTitle(postById.getTitle());
            pr.setCreatedAt(postById.getCreatedAt());
            return pr;
        }
        return null;
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
    public PostResponseDto createPost(PostRequestDto postRequestDto) {

        User author = userRepository.findById(postRequestDto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Category category = categoryRepository.findById(postRequestDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Set<Tag> tagsFromDb = new HashSet<>();
        for (Long tagId : postRequestDto.getTagIds()) {
            Tag tagFromDb = tagRepository.findById(tagId)
                    .orElseThrow(() -> new RuntimeException("Tag not found with ID: " + tagId));
            tagsFromDb.add(tagFromDb);
        }

        Post p = new Post();
        p.setTitle(postRequestDto.getTitle());
        p.setContent(postRequestDto.getContent());
        p.setCreatedAt(LocalDateTime.now());
        p.setUpdatedAt(LocalDateTime.now());
        p.setAuthor(author);
        p.setCategory(category);
        p.setTags(tagsFromDb);
        p.setComments(new ArrayList<>());

        Post createdPost = postRepository.save(p);

        PostResponseDto pr = new PostResponseDto();
        pr.setId(createdPost.getId());
        pr.setAuthorName(createdPost.getAuthor().getUsername());
        pr.setCategory(createdPost.getCategory().getName());
        pr.setContent(createdPost.getContent());

        List<String>tagNames = new ArrayList<>();
        for(Tag t: createdPost.getTags()){
            tagNames.add(t.getName());
        }
        pr.setTags(tagNames);
        pr.setTitle(createdPost.getTitle());
        pr.setCreatedAt(createdPost.getCreatedAt());
        return pr;

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
       Optional<Post> post = this.postRepository.findById(id);
        if (post.isPresent()) {
            post.get().setTitle(postDetails.getTitle());
            post.get().setContent(postDetails.getContent());
            post.get().setCategory(postDetails.getCategory());
            post.get().setTags(postDetails.getTags());
            return postRepository.save(post.get());
        }
        return null;



    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);

    }
}
