package com.ptjcoding.nbcampspringnewsfeed.domain.post.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces.CommentRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.MemberService;
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
  private final MemberService memberService;
  private final CommentRepository commentRepository;

  @Override
  @Transactional
  public Post createPost(PostRequestDto postRequestDto, Long memberId) {
    Member member = memberService.getMemberByMemberId(memberId);
    return postRepository.createPost(postRequestDto, member);
  }

  @Override
  public List<Post> getPosts() {
    return postRepository.getPosts();
  }

  @Override
  @Transactional
  public Post updatePost(Long postId, PostRequestDto postRequestDto) {
    return postRepository.updatePost(postId, postRequestDto);
  }

  @Override
  public Post getPostByPostId(Long postId) {
    Member member = memberService.getMemberByMemberId(postId);
    List<Comment> commentList = commentRepository.getCommentsByPostId(postId);
    return postRepository.getPostByPostId(postId, member, commentList);
  }

}