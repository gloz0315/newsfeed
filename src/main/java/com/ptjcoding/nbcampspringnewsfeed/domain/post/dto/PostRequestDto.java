package com.ptjcoding.nbcampspringnewsfeed.domain.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {

  @NotNull(message = "회원아이디는 필수입력 항목입니다.")
  private Long memberId;
  @NotBlank(message = "글 제목은 필수입력 항목입니다.")
  @Size(max = 40, message = "글 제목은 40자 이하여야 합니다.")
  private String title;
  @NotBlank(message = "글 내용은 필수입력 항목입니다.")
  @Size(max = 500, message = "글 내용은 500자 이하여야 합니다.")
  private String content;
}
