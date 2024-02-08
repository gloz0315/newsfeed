package com.ptjcoding.nbcampspringnewsfeed.global.jwt;

import com.ptjcoding.nbcampspringnewsfeed.global.exception.jwt.CustomJwtException;
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
import java.util.UUID;

import static com.ptjcoding.nbcampspringnewsfeed.global.exception.jwt.JwtErrorCode.INVALID_TOKEN_EXCEPTION;


@Slf4j
@Component
public class JwtProvider {
    private static final String AUTHORIZATION_ACCESS_TOKEN_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_REFRESH_TOKEN_HEADER_KEY = "RefreshToken";
    private static final String AUTHORIZATION_KEY = "Auth";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final Integer BEARER_PREFIX_LENGTH = 7;
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private static final Long ACCESS_TOKEN_VALID_TIME = (60 * 1000L) * 30;
    private static final Long REFRESH_TOKEN_VALID_TIME = (60 * 1000L) * 60 * 24 * 7;

    @Value("${jwt.secret_key}")
    private String secretKey;
    private Key key;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String generateAccessToken(final String email, final String role) {
        return generateToken(email, role, ACCESS_TOKEN_VALID_TIME);
    }

    public String generateRefreshToken(final String role) {
        String uuid = UUID.randomUUID().toString();
        return generateToken(uuid, role, REFRESH_TOKEN_VALID_TIME);
    }

    private String generateToken(
            final String email,
            final String role,
            Long validTime
    ) {
        Date now = new Date();
        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(email)
                        .claim(AUTHORIZATION_KEY, role)
                        .setExpiration(new Date(now.getTime() + validTime))
                        .setIssuedAt(now)
                        .signWith(key, SIGNATURE_ALGORITHM)
                        .compact();
    }

    private void addTokenToCookie(final String token,
                                 final String headerField,
                                 final HttpServletResponse response){
        String newToken = URLEncoder.encode(token, StandardCharsets.UTF_8)
                .replace("\\+", "%20");

        Cookie cookie = new Cookie(headerField, newToken);
        cookie.setPath("/");

        response.addCookie(cookie);
    }
    public void addAccessTokenToCookie(final String token, final HttpServletResponse response) {
        addTokenToCookie(token, AUTHORIZATION_ACCESS_TOKEN_HEADER_KEY, response);
    }

    public void addRefreshTokenToCookie(final String token, final HttpServletResponse response) {
        addTokenToCookie(token, AUTHORIZATION_REFRESH_TOKEN_HEADER_KEY, response);
    }

    public String substringToken(final String tokenValue) {
        if (!StringUtils.hasText(tokenValue) || !tokenValue.startsWith(BEARER_PREFIX)) {
            throw new CustomJwtException(INVALID_TOKEN_EXCEPTION);
        }

        return tokenValue.substring(BEARER_PREFIX_LENGTH);
    }

    public Claims getMemberInfoFromToken(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getRoleFromClaim(Claims claims){
        return (String) claims.get(AUTHORIZATION_KEY);
    }


    public String getAccessTokenFromRequest(final HttpServletRequest request){
        return getTokenFromRequest(request, AUTHORIZATION_ACCESS_TOKEN_HEADER_KEY);
    }

    public String getRefreshTokenFromRequest(final HttpServletRequest request){
        return getTokenFromRequest(request, AUTHORIZATION_ACCESS_TOKEN_HEADER_KEY);
    }

    private String getTokenFromRequest(final HttpServletRequest request,
                                      final String headerField) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            throw new CustomJwtException(INVALID_TOKEN_EXCEPTION);
        }

        Cookie findCookie = Arrays.stream(cookies)
                .filter(cookie ->
                        cookie.getName().equals(headerField))
                .findFirst()
                .orElseThrow(() ->
                        new CustomJwtException(INVALID_TOKEN_EXCEPTION));

        return URLDecoder.decode(findCookie.getValue(), StandardCharsets.UTF_8);
    }

    public boolean validate(final String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException |
                 SignatureException e) {
            log.error("[Invalid JWT signature]", e);
        } catch (ExpiredJwtException e) {
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
                        cookie.getName().equals(AUTHORIZATION_ACCESS_TOKEN_HEADER_KEY))
                .findFirst()
                .ifPresent(cookie -> cookie.setMaxAge(-1));
    }
}
