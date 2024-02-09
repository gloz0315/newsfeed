package com.ptjcoding.nbcampspringnewsfeed.domain.post.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.MemberRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.infrastructure.PostJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.infrastructure.entity.PostEntity;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

  private final MemberRepository memberRepository;
  private final PostJpaRepository postJpaRepository;

  @Override
  @Transactional
  public Post createPost(PostRequestDto postRequestDto, Long memberId) {
    Member member = memberRepository.findByIdOrElseThrow(memberId);
    return postJpaRepository.save(PostEntity.of(postRequestDto, memberId))
        .toModel(member.getNickname());
  }

  @Override
  public List<Post> getPost() {
    List<PostEntity> postEntityList = postJpaRepository.findAll();
    List<Post> postList = new ArrayList<>();
    for (PostEntity postEntity : postEntityList) {
      Member member = memberRepository.findByIdOrElseThrow(postEntity.getMemberId());
      postList.add(Post.of(postEntity, member.getNickname()));
    }
    return postList;
  }
}
