package com.ptjcoding.nbcampspringnewsfeed.domain.post.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.repository.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
  private final PostRepository postRepository;
  @Override
  @Transactional
  public Post createPost(PostRequestDto postRequestDto, Long memberId) {
    return postRepository.createPost(postRequestDto, memberId);
  }

  @Override
  public List<Post> getPosts() {
    return postRepository.getPost();
  }

  @Override
  @Transactional
  public Post updatePost(Long postId, PostRequestDto postRequestDto) {
    return postRepository.updatePost(postId, postRequestDto);
  }
}