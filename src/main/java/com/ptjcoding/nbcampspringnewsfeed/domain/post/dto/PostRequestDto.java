package com.ptjcoding.nbcampspringnewsfeed.domain.post.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class PostRequestDto {

  @NotBlank(message = "글 제목은 필수입력 항목입니다.")
  @Length(max = 40, message = "글 제목은 40자 이하여야 합니다.")
  private String title;
  @NotBlank(message = "글 내용은 필수입력 항목입니다.")
  @Length(max = 500, message = "글 내용은 500자 이하여야 합니다.")
  private String content;
}
