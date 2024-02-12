package com.ptjcoding.nbcampspringnewsfeed.domain.post.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.service.CommentService;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.MemberService;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.repository.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
  private final PostRepository postRepository;
  private final MemberService memberService;
  private final CommentService commentService;

  @Override
  public PostResponseDto createPost(PostRequestDto postRequestDto, Long memberId) {
    Member member = memberService.getMemberByMemberId(memberId);
    Post post = postRepository.createPost(postRequestDto, member);
    return PostResponseDto.from(post, member.getNickname(), null);
  }

  @Override
  @Transactional(readOnly = true)
  public List<PostResponseDto> getPosts() {
    return postRepository.getPosts()
        .stream()
        .map(post -> {
          Member member = memberService.getMemberByMemberId(post.getMemberId());
          List<Comment> commentList = commentService.getCommentsByPostId(post.getPostId());
          return PostResponseDto.from(post, member.getNickname(), commentList);
        }).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public PostResponseDto getPost(Long postId) {
    Post post = postRepository.getPost(postId);
    Member member = memberService.getMemberByMemberId(post.getMemberId());
    List<Comment> commentList = commentService.getCommentsByPostId(postId);
    return PostResponseDto.from(post, member.getNickname(), commentList);
  }

  @Override
  public PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto, Long memberId) {
    Post post = postRepository.getPost(postId);
    if (!post.getMemberId().equals(memberId)) {
      throw new IllegalArgumentException("Member id not matching");
    }
    post = postRepository.updatePost(postId, postRequestDto);
    Member member = memberService.getMemberByMemberId(post.getMemberId());
    List<Comment> commentList = commentService.getCommentsByPostId(postId);
    return PostResponseDto.from(post, member.getNickname(), commentList);
  }

  @Override
  public void deletePost(Long postId, Long memberId) {
    Post post = postRepository.getPost(postId);
    if (!post.getMemberId().equals(memberId)) {
      throw new IllegalArgumentException("Member id not matching");
    }
    commentService.deleteCommentsByPostId(postId);
    postRepository.deletePost(postId);
  }

  @Override
  public Post getPostByPostId(Long postId) {
    return postRepository.getPostByPostId(postId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Post> getPostsByMemberId(Long memberId) {
    Member member = memberService.getMemberByMemberId(memberId);
    return postRepository.getPostsByMemberId(member.getId());
  }

  @Override
  public void deletePostsByMemberId(Long memberId) {
    Member member = memberService.getMemberByMemberId(memberId);
    postRepository.deletePostsByMemberId(member.getId());
  }

}