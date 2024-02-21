package com.ptjcoding.nbcampspringnewsfeed.test;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.MemberRole;

public class TestInfo {

  // SUCCESS
  public static final Long TEST_ID = 1L;
  public static final String TEST_EMAIL = "test1@naver.com";
  public static final String TEST_NICKNAME = "TEST1";
  public static final String TEST_PASSWORD = "Sk123456789";
  public static final String TEST_OTHER_PASSWORD = "Sk123456780";
  public static final MemberRole TEST_ROLE = MemberRole.USER;
  public static final String CHANGE_NICKNAME = "CHANGE1";


  // FAIL
  public static final Long TEST_FAIL_ID = 1L;
  public static final String TEST_FAIL_EMAIL = "test1naver.com";
  public static final String TEST_FAIL_NICKNAME = "TESTFAILNAMELENGTH20OVERNAMES";
  public static final String TEST_FAIL_PASSWORD = "sk123456789";
  public static final MemberRole TEST_FAIL_ROLE = MemberRole.USER;
  public static final String CHANGE_FAIL_NICKNAME = "CHANGE1";

}
