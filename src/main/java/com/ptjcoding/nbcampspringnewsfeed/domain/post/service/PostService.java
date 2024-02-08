package com.ptjcoding.nbcampspringnewsfeed.domain.post.service;


import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostResponseDto;


public interface PostService {

  PostResponseDto createPost(PostRequestDto postRequestDto);
}
