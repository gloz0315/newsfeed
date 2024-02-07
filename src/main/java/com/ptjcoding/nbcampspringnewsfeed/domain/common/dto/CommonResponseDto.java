package com.ptjcoding.nbcampspringnewsfeed.domain.common.dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponseDto<T> {
	private HttpStatus status;
	private String message;
	private T data;

	public String getStatus() {
		return status.toString();
	}
}
