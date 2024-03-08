package com.example.auth.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

//    @PostMapping("/posts")
//    public ResponseEntity<PostEntity> addPost(@RequestBody PostEntity post) {
//        PostEntity addedPost = postService.addPost(post);
//        return new ResponseEntity<>(addedPost, HttpStatus.CREATED);
//    }
@PostMapping("/addPost")
public ResponseEntity<PostEntity> savedPost(@RequestParam("mark") String mark,
                                           @RequestParam("price") double price,
                                           @RequestParam("id_admin") Long dminId,
                                           @RequestParam("model") String model,
                                           @RequestParam("description") String description,
                                           @RequestParam("image") MultipartFile image) {
    try {
        PostEntity post = new PostEntity();
        post.setMark(mark);
        post.setAdminId(dminId);
        post.setPrice(price);
        post.setModel(model);
        post.setDescription(description);
        post.setImage(image.getBytes());
        PostEntity savedPost = postService.addPost(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
    @GetMapping("getAllPosts")
    public ResponseEntity<List<PostEntity>> getAllPosts() {
        try {
            List<PostEntity> posts = postService.getAllPosts();
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getByIdAdmin/{adminId}")
    public ResponseEntity<List<PostEntity>> getPostsByAdminId(@PathVariable Long adminId) {
        List<PostEntity> posts = postService.getPostsByAdminId(adminId);
        return ResponseEntity.ok(posts);
    }
}
