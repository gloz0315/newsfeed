package com.ptjcoding.nbcampspringnewsfeed.global.exception;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.jwt.CustomJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomJwtException.class)
    public ResponseEntity<CommonResponseDto<String>> jwtExceptionHandler(CustomJwtException e) {
        return CommonResponseDto.badRequest(e.getMessage());
    }
}
