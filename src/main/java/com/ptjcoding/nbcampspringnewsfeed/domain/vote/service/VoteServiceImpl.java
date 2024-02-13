package com.ptjcoding.nbcampspringnewsfeed.domain.vote.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces.CommentRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.MemberRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.repository.PostRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteCreateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.model.Vote;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto.VoteCreateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto.VoteUpdateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.interfaces.VoteRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
@Transactional
public class VoteServiceImpl implements VoteService {

  private final VoteRepository voteRepository;
  private final PostRepository postRepository;
  private final CommentRepository commentRepository;
  private final MemberRepository memberRepository;

  @Override
  public VoteResponseDto createVote(Member member, VoteCreateRequestDto requestDto) {
    Long memberId = member.getId();
    Long postId = requestDto.getPostId();

    postRepository.findPostOrElseThrow(postId);
    Optional<Vote> vote = voteRepository.findVoteByMemberIdAndPostId(memberId, postId);

    if (vote.isPresent()) {
      throw new RuntimeException("투표가 이미 존재함");
    }

    VoteCreateDto createDto = VoteCreateDto.of(memberId, requestDto);

    if (Boolean.TRUE.equals(requestDto.getIsAgree())) {
      postRepository.upAgreeCount(postId);
    } else {
      postRepository.upDisagreeCount(postId);
    }

    String memberNickname = memberRepository.findMemberOrElseThrow(memberId).getNickname();
    return VoteResponseDto.of(voteRepository.createVote(createDto), memberNickname);
  }

  @Override
  public VoteResponseDto updateVote(Member member, Long voteId, VoteUpdateRequestDto requestDto) {
    Long memberId = member.getId();

    Vote vote = voteRepository.findVoteOrElseThrow(voteId);

    if (!vote.checkMemberIdEqual(memberId)) {
      throw new RuntimeException("접근 권한이 없습니다.");
    }
    if (vote.checkIsAgreeEqual(requestDto.getIsAgree())) {
      throw new IllegalArgumentException("변경 사항이 없습니다.");
    }

    Long postId = vote.getPostId();
    if (requestDto.getIsAgree()) {
      postRepository.upAgreeCount(postId);
      postRepository.downDisagreeCount(postId);
    } else {
      postRepository.downAgreeCount(postId);
      postRepository.upDisagreeCount(postId);
    }

    VoteUpdateDto updateDto = VoteUpdateDto.of(requestDto);
    String memberNickname = memberRepository.findMemberOrElseThrow(memberId).getNickname();

    return VoteResponseDto.of(voteRepository.updateVote(voteId, updateDto), memberNickname);
  }

  public void deleteVote(Member member, Long voteId, Boolean isSafe) {
    Vote vote = voteRepository.findVoteOrElseThrow(voteId);
    Long postId = vote.getPostId();

    List<Comment> comments = commentRepository.findCommentsByMemberIdAndPostId(member.getId(), postId);

    boolean isSafeDeletion = (isSafe != null) && isSafe && !comments.isEmpty();
    if (isSafeDeletion) {
      throw new RuntimeException("해당 게시글의 댓글을 먼저 삭제해주세요.");
    }

    if (Boolean.TRUE.equals(vote.getIsAgree())) {
      postRepository.downAgreeCount(postId);
    } else {
      postRepository.downDisagreeCount(postId);
    }

    commentRepository.deleteCommentsByMemberIdAndPostId(member.getId(), postId);
    voteRepository.deleteVote(voteId);
  }

}
