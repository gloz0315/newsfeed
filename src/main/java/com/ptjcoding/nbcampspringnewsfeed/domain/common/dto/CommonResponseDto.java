package com.ptjcoding.nbcampspringnewsfeed.domain.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponseDto<T> {
	private HttpStatus status;
	private String message;
	private T data;

	public static <T> ResponseEntity<CommonResponseDto<T>> ok(String message, T data){
		return ResponseEntity
				.ok(new CommonResponseDto<>(HttpStatus.OK, message, data));
	}

	public String getStatus() {
		return status.toString();
	}
}
