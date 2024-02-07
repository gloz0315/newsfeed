package com.ptjcoding.nbcampspringnewsfeed.global.interceptor;

import com.ptjcoding.nbcampspringnewsfeed.global.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Object handler
    ) throws Exception {
        if (isSwaggerRequest(request)) {
            return true;
        }

        String tokenValue = jwtUtil.getTokenFromRequest(request);
        String token = jwtUtil.substringToken(tokenValue);
        Claims memberInfo = jwtUtil.getMemberInfoFromToken(token);

        // TODO: member repository is not yet
//        Member member = memberRepository.findByEmail(memberInfo.getSubject())
//                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_EXCEPTION));
//        request.setAttribute("member", member);

        return true;
    }

    private boolean isSwaggerRequest(final HttpServletRequest request) {
        String uri = request.getRequestURI();
        return uri.contains("swagger")
                || uri.contains("api-docs")
                || uri.contains("webjars");
    }
}
