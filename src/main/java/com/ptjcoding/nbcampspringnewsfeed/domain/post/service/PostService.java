package com.ptjcoding.nbcampspringnewsfeed.domain.post.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import java.util.List;

public interface PostService {

  PostResponseDto createPost(PostRequestDto postRequestDto, Long memberId);

  List<PostResponseDto> getPosts();

  PostResponseDto getPost(Long postId);

  PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto, Long memberId);

  void deletePost(Long postId, Long memberId);

  public List<Post> getPostsByMemberId(Long memberId);

  public void deletePostsByMemberId(Long memberId);
}
