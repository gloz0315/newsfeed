package com.ptjcoding.nbcampspringnewsfeed.domain.post.service;


import com.ptjcoding.nbcampspringnewsfeed.domain.member.entity.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.MemberRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.entity.Post;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

  MemberRepository memberRepository;
  PostRepository postRepository;

  @Override
  @Transactional
  public PostResponseDto createPost(PostRequestDto postRequestDto) {
    // Todo: 유효한 회원 검증

    Member member = memberRepository.findById(postRequestDto.getMemberId()).orElse(null);
    if (member == null) {
      throw new EntityNotFoundException(postRequestDto.getMemberId() + " Member not found");
    }

    return new PostResponseDto(
        postRepository.save(new Post(postRequestDto)),
        member.getNickname());
  }
}
