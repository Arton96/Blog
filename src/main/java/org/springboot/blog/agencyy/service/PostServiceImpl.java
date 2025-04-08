package org.springboot.blog.agencyy.service;

import org.springboot.blog.agencyy.entity.Post;
import org.springboot.blog.agencyy.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{


    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
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
        return postRepository.save(post);
    }

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
