package com.ptjcoding.nbcampspringnewsfeed.domain.vote.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces.CommentRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.hall_of_fame.repository.HallOfFameRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.MemberRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.repository.PostRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteCreateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.model.Vote;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto.VoteCreateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto.VoteUpdateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.interfaces.VoteRepository;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.CustomRuntimeException;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.GlobalErrorCode;
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
  private final MemberRepository memberRepository;
  private final CommentRepository commentRepository;
  private final HallOfFameRepository hallOfFameRepository;

  @Override
  public VoteResponseDto createVote(Member member, VoteCreateRequestDto requestDto) {
    Long memberId = member.getId();
    Long postId = requestDto.getPostId();

    Post post = postRepository.findPostOrElseThrow(postId);
    Optional<Vote> vote = voteRepository.findVoteByMemberIdAndPostId(memberId, postId);

    if (vote.isPresent()) {
      throw new RuntimeException();
    }

    VoteCreateDto createDto = VoteCreateDto.of(memberId, requestDto);

    if (Boolean.TRUE.equals(requestDto.getIsAgree())) {
      postRepository.upAgreeCount(postId);
    } else {
      postRepository.upDisagreeCount(postId);
    }

    hallOfFameRepository.updateTable(postId, post.getVoteCount() + 1);
    String memberNickname = memberRepository.findMemberOrElseThrow(memberId).getNickname();
    return VoteResponseDto.of(voteRepository.createVote(createDto), memberNickname);
  }

  @Override
  public VoteResponseDto updateVote(Member member, Long voteId, VoteUpdateRequestDto requestDto) {
    Long memberId = member.getId();

    Vote vote = voteRepository.findVoteOrElseThrow(voteId);

    if (!vote.checkMemberIdEqual(memberId)) {
      throw new CustomRuntimeException(GlobalErrorCode.UNAUTHORIZED);
    }
    if (vote.checkIsAgreeEqual(requestDto.getIsAgree())) {
      throw new CustomRuntimeException(GlobalErrorCode.UNCHANGED);
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
    if (!vote.checkMemberIdEqual(member.getId())) {
      throw new CustomRuntimeException(GlobalErrorCode.UNAUTHORIZED);
    }

    Long postId = vote.getPostId();
    Post post = postRepository.findPostOrElseThrow(postId);

    List<Comment> comments = commentRepository.findCommentsByMemberIdAndPostId(member.getId(), postId);

    boolean isSafeDeletion = (isSafe != null) && isSafe && !comments.isEmpty();
    if (isSafeDeletion) {
      throw new CustomRuntimeException(GlobalErrorCode.SAFEGUARD);
    }

    if (Boolean.TRUE.equals(vote.getIsAgree())) {
      postRepository.downAgreeCount(postId);
    } else {
      postRepository.downDisagreeCount(postId);
    }

    hallOfFameRepository.updateTable(postId, post.getVoteCount() - 1);
    commentRepository.deleteCommentsByMemberIdAndPostId(member.getId(), postId);
    voteRepository.deleteVote(voteId);
  }

}
