package com.example.auth.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostEntity addPost(PostEntity post) {
        return postRepository.save(post);
    }
    public List<PostEntity> getAllPosts() {
        return postRepository.findAll();
    }
    public List<PostEntity> getPostsByAdminId(Long adminId) {
        return postRepository.findByAdminId(adminId);
    }
}
