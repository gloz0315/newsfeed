package com.ptjcoding.nbcampspringnewsfeed.global.exception;

import lombok.Getter;

@Getter
public enum GlobalErrorCode {

  NOT_FOUND("Entity not founds."),
  NOT_EQUAL("Entity not equals."),
  UNAUTHORIZED("can't access."),
  UNCHANGED("Entity remains unchanged."),
  ALREADY_EXIST("Entity already exists."),
  SAFEGUARD("rejected by a safeguard."),
  ILLEGAL_INPUT("wrong input.");
  private final String message;

  GlobalErrorCode(String message) {
    this.message = message;
  }
}
