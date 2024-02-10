package com.ptjcoding.nbcampspringnewsfeed.domain.member.service;

import static com.ptjcoding.nbcampspringnewsfeed.domain.member.model.MemberRole.USER;

import com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.repository.BlackListRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces.CommentRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.SignupRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.MemberRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberSignupDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.repository.PostRepository;
import com.ptjcoding.nbcampspringnewsfeed.global.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;
  // TODO: 댓글과 작성글에 대한 정보를 가져오기 위해 남김
  private final PostRepository postRepository;
  private final CommentRepository commentRepository;
  private final BlackListRepository blackListRepository;
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
  public void logout(HttpServletRequest request) {
    jwtProvider.expireToken(request);
  }

  @Override
  public void delete(Long memberId) {
    // TODO: 회원의 작성글과 댓글 삭제
    memberRepository.deleteMember(memberId);
  }
}
