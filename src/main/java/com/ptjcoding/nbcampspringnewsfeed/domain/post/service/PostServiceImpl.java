package com.ptjcoding.nbcampspringnewsfeed.domain.post.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.MemberService;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
  private final PostRepository postRepository;
  private final MemberService memberService;

  @Override
  public PostResponseDto createPost(PostRequestDto postRequestDto, Long memberId) {
    Member member = memberService.getMemberByMemberId(memberId);
    Post post = postRepository.createPost(postRequestDto, member);
    return PostResponseDto.from(post, member.getNickname(), null);
  }
}