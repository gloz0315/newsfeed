package com.ptjcoding.nbcampspringnewsfeed.global.interceptor;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.infrastructure.entity.MemberEntity;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.infrastructure.MemberJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.global.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final MemberJpaRepository memberRepository;
    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Object handler
    ) throws Exception {
        if (isSwaggerRequest(request)) {
            return true;
        }

        String tokenValue = jwtProvider.getAccessTokenFromRequest(request);
        String token = jwtProvider.substringToken(tokenValue);
        Claims memberInfo = jwtProvider.getMemberInfoFromToken(token);

        try {
            if (StringUtils.hasText(token) && jwtProvider.validate(token)) {
                // TODO: replace custom member exception
                MemberEntity member = memberRepository.findByEmail(memberInfo.getSubject())
                        .orElseThrow(() ->
                                new UsernameNotFoundException("잘못된 접근입니다."));
                request.setAttribute("member", member);
            }
        } catch (ExpiredJwtException e) {
            String refreshTokenValue = jwtProvider.getRefreshTokenFromRequest(request);
            String refreshToken = jwtProvider.substringToken(refreshTokenValue);

            if (StringUtils.hasText(refreshToken) && jwtProvider.validate(refreshToken)) {
                String newAccessToken = jwtProvider
                        .generateAccessToken(memberInfo.getSubject(),
                                jwtProvider.getRoleFromClaim(memberInfo));
                jwtProvider.addAccessTokenToCookie(newAccessToken, response);
            }
        }
        return true;
    }

    private boolean isSwaggerRequest(final HttpServletRequest request) {
        String uri = request.getRequestURI();
        return uri.contains("swagger")
                || uri.contains("api-docs")
                || uri.contains("webjars");
    }
}
