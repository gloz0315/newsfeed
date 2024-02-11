package com.ptjcoding.nbcampspringnewsfeed.domain.post.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.MemberRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.infrastructure.PostJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.infrastructure.entity.PostEntity;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import jakarta.persistence.EntityNotFoundException;
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
  public Post createPost(PostRequestDto postRequestDto, Member member) {
    return postJpaRepository.save(PostEntity.of(postRequestDto, member.getId()))
        .toModel(member.getNickname(), null);
  }

  @Override
  public List<Post> getPosts() {
    List<PostEntity> postEntityList = postJpaRepository.findAllByOrderByCreatedDateDesc();
    List<Post> postList = new ArrayList<>();
    for (PostEntity postEntity : postEntityList) {
      Member member = memberRepository.findByIdOrElseThrow(postEntity.getMemberId());
      postList.add(postEntity.toModel(member.getNickname(), null));
    }
    return postList;
  }

  @Override
  public Post updatePost(Long postId, PostRequestDto postRequestDto) {
    PostEntity postEntity = findByIdOrElseThrow(postId);
    postEntity.update(postRequestDto);
    Member member = memberRepository.findByIdOrElseThrow(postEntity.getMemberId());
    return postEntity.toModel(member.getNickname(), null);
  }

  @Override
  public Post getPostByPostId(Long postId, Member member, List<Comment> commentList) {
    PostEntity postEntity = findByIdOrElseThrow(postId);
    return postEntity.toModel(member.getNickname(), commentList);
  }

  @Override
  public PostEntity findByIdOrElseThrow(Long postId) {
    return postJpaRepository.findById(postId).orElseThrow(
        () -> new EntityNotFoundException(postId + "번 게시글은 존재하지 않습니다.")
    );
  }
}