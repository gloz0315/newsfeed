package com.ptjcoding.nbcampspringnewsfeed.global.enums;

import lombok.Getter;

@Getter
public enum GlobalSuccessCode {
  CREATE("create success."),
  REGISTER("register success."),
  SEARCH("search success."),
  UPDATE("update success."),
  DELETE("delete success."),
  LOGIN("login success."),
  LOGOUT("logout success."),
  ;
  private final String message;

  GlobalSuccessCode(String message) {
    this.message = message;
  }
}
