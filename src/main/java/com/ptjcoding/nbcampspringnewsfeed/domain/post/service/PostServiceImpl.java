package com.ptjcoding.nbcampspringnewsfeed.domain.post.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.service.CommentService;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.MemberService;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.repository.PostRepository;
import java.util.ArrayList;
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
  public List<PostResponseDto> getPosts() {
    List<Post> postList = postRepository.getPosts();
    List<PostResponseDto> postResponseDtoList = new ArrayList<>();
    for (Post post : postList) {
      Member member = memberService.getMemberByMemberId(post.getMemberId());
      List<Comment> commentList = commentService.getCommentsByPostId(post.getPostId());
      postResponseDtoList.add(PostResponseDto.from(post, member.getNickname(), commentList));
    }
    return postResponseDtoList;
  }
}