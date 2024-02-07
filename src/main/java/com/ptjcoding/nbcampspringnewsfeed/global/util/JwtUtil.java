package com.ptjcoding.nbcampspringnewsfeed.global.util;

import com.ptjcoding.nbcampspringnewsfeed.global.exception.jwt.CustomJwtException;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.jwt.JwtErrorCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import static com.ptjcoding.nbcampspringnewsfeed.global.exception.jwt.JwtErrorCode.*;


@Slf4j
@Component
public class JwtUtil {
    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_KEY = "Auth";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final Integer BEARER_PREFIX_LENGTH = 7;
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM =SignatureAlgorithm.HS256;
    private static final Long TOKEN_VALID_TIME = 60 * 60 * 24 * 1000L;

    @Value("${jwt.secret}")
    private String secretKey;
    private Key key;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String generateToken(final String email, final String role) {
        Date now = new Date();
        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(email)
                        .claim(AUTHORIZATION_KEY, role)
                        .setExpiration(new Date(now.getTime() + TOKEN_VALID_TIME))
                        .setIssuedAt(now)
                        .signWith(key, SIGNATURE_ALGORITHM)
                        .compact();
    }

    public void addTokenToCookie(final String token, final HttpServletResponse response) {
        String newToken = URLEncoder.encode(token, StandardCharsets.UTF_8)
                .replace("\\+", "%20");

        Cookie cookie = new Cookie(AUTHORIZATION_HEADER_KEY, newToken);
        cookie.setPath("/");

        response.addCookie(cookie);
    }

    public String substringToken(final String tokenValue) {
        if (!StringUtils.hasText(tokenValue) || !tokenValue.startsWith(BEARER_PREFIX)) {
            throw new CustomJwtException(INVALID_TOKEN_EXCEPTION);
        }

        String token = tokenValue.substring(BEARER_PREFIX_LENGTH);

        if (!validate(token)){
            throw new CustomJwtException(INVALID_TOKEN_EXCEPTION);
        }

        return token;
    }

    public Claims getMemberInfoFromToken(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getTokenFromRequest(final HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            throw new CustomJwtException(INVALID_TOKEN_EXCEPTION);
        }

        Cookie findCookie = Arrays.stream(cookies)
                .filter(cookie ->
                        cookie.getName().equals(AUTHORIZATION_HEADER_KEY))
                .findFirst()
                .orElseThrow(() ->
                        new CustomJwtException(INVALID_TOKEN_EXCEPTION));

        return URLDecoder.decode(findCookie.getValue(), StandardCharsets.UTF_8);
    }

    private boolean validate(final String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException |
                 SignatureException e) {
            log.error("[Invalid JWT signature]", e);
        } catch (ExpiredJwtException e) {
            // TODO: refactoring for auto authentication
            log.error("[Expired JWT token]", e);
        } catch (UnsupportedJwtException e) {
            log.error("[Unsupported JWT token]", e);
        } catch (IllegalArgumentException e) {
            log.error("[JWT claims is empty]", e);
        }

        return false;
    }

    public void expireToken(final HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Arrays.stream(cookies)
                .filter(cookie ->
                        cookie.getName().equals(AUTHORIZATION_HEADER_KEY))
                .findFirst()
                .ifPresent(cookie -> cookie.setMaxAge(-1));
    }
}
