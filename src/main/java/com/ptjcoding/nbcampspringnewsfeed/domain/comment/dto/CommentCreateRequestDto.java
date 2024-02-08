package com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class CommentCreateRequestDto {

  @NotNull
  @NotBlank
  @Length(max = 500)
  private String content;

  @NotNull
  @Positive
  private Long postId;

  @Positive
  private Long parentCommentId;

}
