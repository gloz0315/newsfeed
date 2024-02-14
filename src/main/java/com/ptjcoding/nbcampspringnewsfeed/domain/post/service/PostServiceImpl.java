package com.ptjcoding.nbcampspringnewsfeed.domain.post.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.repository.BookmarkRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces.CommentRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.hall_of_fame.repository.HallOfFameRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.MemberRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.repository.PostRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.interfaces.VoteRepository;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.CustomRuntimeException;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.GlobalErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

  private final MemberRepository memberRepository;
  private final PostRepository postRepository;
  private final CommentRepository commentRepository;
  private final VoteRepository voteRepository;
  private final BookmarkRepository bookmarkRepository;
  private final HallOfFameRepository hallOfFameRepository;

  @Override
  public PostResponseDto createPost(PostRequestDto postRequestDto, Long memberId) {
    Member member = memberRepository.findMemberOrElseThrow(memberId);
    Post post = postRepository.createPost(postRequestDto, member);
    return PostResponseDto.fromPost(post, member.getNickname());
  }

  @Override
  @Transactional(readOnly = true)
  public List<PostResponseDto> getPosts() {
    return postRepository.findPosts()
        .stream()
        .map(post -> {
          Member member = memberRepository.findMemberOrElseThrow(post.getMemberId());
          return PostResponseDto.fromPost(post, member.getNickname());
        })
        .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public PostResponseDto getPost(Long postId) {
    Post post = postRepository.findPostOrElseThrow(postId);
    Member member = memberRepository.findMemberOrElseThrow(post.getMemberId());
    List<Comment> commentList = commentRepository.findCommentsByPostId(post.getPostId());
    return PostResponseDto.fromPostDetail(post, member.getNickname(), commentList);
  }

  @Override
  public List<PostResponseDto> getHallOfFame() {
    return hallOfFameRepository.getTop3PostIdList().stream()
        .map(postId -> {
          Post post = postRepository.findPostOrElseThrow(postId);
          Member member = memberRepository.findMemberOrElseThrow(post.getMemberId());
          return PostResponseDto.fromPost(post, member.getNickname());
        })
        .toList();
  }

  @Override
  public PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto, Long memberId) {
    Post post = postRepository.findPostOrElseThrow(postId);
    if (post.isNotEqualsMemberId(memberId)) {
      throw new CustomRuntimeException(GlobalErrorCode.UNAUTHORIZED);
    }
    Post updatePost = postRepository.updatePost(postId, postRequestDto);
    Member member = memberRepository.findMemberOrElseThrow(post.getMemberId());
    List<Comment> commentList = commentRepository.findCommentsByPostId(postId);
    return PostResponseDto.fromPostDetail(updatePost, member.getNickname(), commentList);
  }

  @Override
  public void deletePost(Long postId, Long memberId) {
    Post post = postRepository.findPostOrElseThrow(postId);
    if (post.isNotEqualsMemberId(memberId)) {
      throw new CustomRuntimeException(GlobalErrorCode.UNAUTHORIZED);
    }

    voteRepository.deleteVotesByPostId(postId);
    commentRepository.deleteCommentsByPostId(postId);
    bookmarkRepository.deleteBookmarksByPostId(postId);
    hallOfFameRepository.deleteHallOfFame(postId);
    postRepository.deletePost(postId);

  }
}