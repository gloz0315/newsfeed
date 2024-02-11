package com.ptjcoding.nbcampspringnewsfeed.domain.post.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import java.util.List;

public interface PostRepository {

  Post createPost(PostRequestDto postRequestDto, Member member);

  List<Post> getPosts();

  Post getPost(Long postId);

  Post findByIdOrElseThrow(Long postId);

  Post updatePost(Long postId, PostRequestDto postRequestDto);

  void deletePost(Long postId);

  List<Post> getPostsByMemberId(Long memberId);

  void deletePostsByMemberId(Long memberId);
}
