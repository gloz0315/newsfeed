package com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class CommentUpdateRequestDto {

  @NotBlank
  @Length(max = 500)
  private String content;

}
