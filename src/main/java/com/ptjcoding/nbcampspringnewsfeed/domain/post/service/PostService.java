package com.ptjcoding.nbcampspringnewsfeed.domain.post.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import java.util.List;

public interface PostService {

  Post createPost(PostRequestDto postRequestDto, Long memberId);

  List<Post> getPosts();

  Post updatePost(Long postId, PostRequestDto postRequestDto);
}
