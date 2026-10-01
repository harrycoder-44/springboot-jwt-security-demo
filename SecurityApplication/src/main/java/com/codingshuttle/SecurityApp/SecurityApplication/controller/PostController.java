package com.codingshuttle.SecurityApp.SecurityApplication.controller;


import com.codingshuttle.SecurityApp.SecurityApplication.dto.PostDTO;
import com.codingshuttle.SecurityApp.SecurityApplication.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posting")
public class PostController {

    private final PostService postService;



    @GetMapping
    public List<PostDTO> getAllPosts(){
        return postService.getAllPost();
    }

    @GetMapping("/{postId}")
    public PostDTO getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }


    @PostMapping
    public PostDTO createPost(@RequestBody PostDTO postDTO){
        return postService.createPost(postDTO);
    }

    @PutMapping("/{postId}")
    public PostDTO updatePost(@RequestBody PostDTO postDTO, @PathVariable Long postId){
        return postService.updatePost(postId,postDTO);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId){
         postService.deletePost(postId);
    }

}
