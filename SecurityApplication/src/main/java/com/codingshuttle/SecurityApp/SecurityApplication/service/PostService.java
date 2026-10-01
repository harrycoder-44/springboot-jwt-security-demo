package com.codingshuttle.SecurityApp.SecurityApplication.service;

import com.codingshuttle.SecurityApp.SecurityApplication.Exceptions.ResourceNotFound;
import com.codingshuttle.SecurityApp.SecurityApplication.dto.PostDTO;
import com.codingshuttle.SecurityApp.SecurityApplication.entity.PostEntity;
import com.codingshuttle.SecurityApp.SecurityApplication.entity.User;
import com.codingshuttle.SecurityApp.SecurityApplication.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {


    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    public List<PostDTO> getAllPost() {

        List<PostEntity> postEntities = postRepository.findAll();
        return postEntities.stream().map(postEntity -> modelMapper.map(postEntity, PostDTO.class)).collect(Collectors.toList());


    }



    public PostDTO getPostById(Long postId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("user {}",user);
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFound("resource not found"));
        return modelMapper.map(postEntity,PostDTO.class);

    }


    public PostDTO createPost(PostDTO postDTO) {
        PostEntity postEntity = modelMapper.map(postDTO,PostEntity.class);
        PostEntity savedPost = postRepository.save(postEntity);
        return modelMapper.map(savedPost,PostDTO.class);


    }


    public PostDTO updatePost(Long postId, PostDTO postDTO) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFound("resource not found"));
        postDTO.setId(postId);
        modelMapper.map(postDTO,postEntity);
        PostEntity savedPostEntity = postRepository.save(postEntity);
        return modelMapper.map(savedPostEntity,PostDTO.class);
    }


    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
