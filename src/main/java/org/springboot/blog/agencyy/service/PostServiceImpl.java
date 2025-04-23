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
    public List<PostResponseDto> getAllPosts() {
        List<Post>posts = this.postRepository.findAll();
        List<PostResponseDto> prs = new ArrayList<>();
        for( Post postById : posts ){
            PostResponseDto pr = new PostResponseDto();

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
            prs.add(pr);
        }
        return prs;
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

    @Override
    public PostResponseDto updatePost(Long id, PostRequestDto postDetails) {
       Optional<Post> post = this.postRepository.findById(id);
        if (post.isPresent()) {


           if(!postDetails.getTitle().isEmpty()){
               post.get().setTitle(postDetails.getTitle());
           }


            post.get().setContent(postDetails.getContent());
            Category category = categoryRepository.findById(postDetails.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            post.get().setCategory(category);

            List<Tag> tagList = tagRepository.findAllById(postDetails.getTagIds());
            Set<Tag> tagSet = new HashSet<>(tagList);
            post.get().setTags(tagSet);

            Post postById = postRepository.save(post.get());
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
        return null;



    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);

    }

    @Override
    public List<PostResponseDto> getPostsByCategory(Long categoryId) {
        List<Post> posts = postRepository.findByCategoryId(categoryId);

        List<PostResponseDto> responseList = new ArrayList<>();

        for (Post post : posts) {
            PostResponseDto pr = new PostResponseDto();
            pr.setId(post.getId());
            pr.setAuthorName(post.getAuthor().getUsername());
            pr.setCategory(post.getCategory().getName());
            pr.setContent(post.getContent());

            List<String> tagNames = new ArrayList<>();
            for (Tag t : post.getTags()) {
                tagNames.add(t.getName());
            }

            pr.setTags(tagNames);
            pr.setTitle(post.getTitle());
            pr.setCreatedAt(post.getCreatedAt());

            responseList.add(pr);
        }

        return responseList;
    }

    @Override
    public List<PostResponseDto> getPostsByAuthorId(Long authorId) {
        List<Post> posts = postRepository.findByAuthorId(authorId);
        List<PostResponseDto> postResponse = new ArrayList<>();
        for(Post post : posts){
            PostResponseDto prs = new PostResponseDto();
            prs.setId(post.getId());
            prs.setAuthorName(post.getAuthor().getUsername());
            prs.setCategory(post.getCategory().getName());
            prs.setContent(post.getContent());

            List<String> tagNames = new ArrayList<>();
            for(Tag t : post.getTags()){
                tagNames.add(t.getName());
            }
            prs.setTags(tagNames);
            prs.setTitle(post.getTitle());
            prs.setCreatedAt(post.getCreatedAt());

            postResponse.add(prs);
        }
        return postResponse;
    }

    @Override
    public List<PostResponseDto> getPostsByTagName(String tagName) {
        List<Post> posts = postRepository.findByTagsName(tagName);
        List<PostResponseDto> responseList = new ArrayList<>();
        for(Post post : posts){
            PostResponseDto pr = new PostResponseDto();
            pr.setTitle(post.getTitle());
            pr.setId(post.getId());
            pr.setAuthorName(post.getAuthor().getUsername());
            pr.setContent(post.getContent());

            List<String> tagNames = new ArrayList<>();
            for(Tag t : post.getTags()){
                tagNames.add(t.getName());
            }
            pr.setTags(tagNames);
            pr.setCreatedAt(post.getCreatedAt());

            responseList.add(pr);
        }
        return responseList;
    }
}
