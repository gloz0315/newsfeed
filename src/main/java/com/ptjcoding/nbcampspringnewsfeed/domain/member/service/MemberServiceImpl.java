package com.ptjcoding.nbcampspringnewsfeed.domain.member.service;

import static com.ptjcoding.nbcampspringnewsfeed.domain.member.model.MemberRole.USER;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces.CommentRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.SignupRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.MemberRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberInfoDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberSignupDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.repository.PostRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.interfaces.VoteRepository;
import com.ptjcoding.nbcampspringnewsfeed.global.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;
  private final PostRepository postRepository;
  private final CommentRepository commentRepository;
  private final VoteRepository voteRepository;
  private final JwtProvider jwtProvider;

  @Override
  public MemberResponseDto signup(SignupRequestDto dto) {
    MemberSignupDto memberSignupDto = new MemberSignupDto(dto.getEmail(), dto.getNickname(),
        dto.getPassword(),
        dto.getCheckPassword());

    memberRepository.checkEmail(memberSignupDto.getEmail());
    memberRepository.register(memberSignupDto);

    return MemberResponseDto.builder()
        .email(dto.getEmail())
        .nickname(dto.getNickname())
        .build();
  }

  @Override
  public void login(LoginRequestDto dto, HttpServletResponse response) {
    Member member = memberRepository.checkPassword(dto);

    String accessToken = jwtProvider.generateAccessToken(member.getId(), USER.getAuthority());
    String refreshToken = jwtProvider.generateRefreshToken(member.getId(), USER.getAuthority());

    jwtProvider.addAccessTokenToCookie(accessToken, response);
    jwtProvider.addRefreshTokenToCookie(refreshToken, response);
  }

  @Override
  @Transactional(readOnly = true)
  public void logout(HttpServletRequest request) {
    jwtProvider.expireToken(request);
  }

  @Override
  public void delete(Long memberId) {
    voteRepository.deleteVotesByMemberId(memberId);
    commentRepository.deleteCommentsByMemberId(memberId);
    postRepository.deletePostsByMemberId(memberId);
    memberRepository.deleteMember(memberId);
  }

  @Override
  @Transactional(readOnly = true)
  public MemberInfoDto memberInfo(Long memberId) {
    Member member = memberRepository.findMemberOrElseThrow(memberId);
    List<Post> postList = postRepository.findPostsByMemberId(memberId);
    List<Comment> commentList = commentRepository.findCommentsByMemberId(memberId);

    return MemberInfoDto.of(member, postList, commentList);
  }
}
