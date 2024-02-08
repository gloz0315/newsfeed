package com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto;

import static com.ptjcoding.nbcampspringnewsfeed.domain.comment.etc.CommentRequestError.CommentRequestErrorMessage.CONTENT_BLANK;
import static com.ptjcoding.nbcampspringnewsfeed.domain.comment.etc.CommentRequestError.CommentRequestErrorMessage.CONTENT_MAX_LENGTH;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class CommentUpdateRequestDto {

  @NotBlank(message = CONTENT_BLANK)
  @Length(max = 500, message = CONTENT_MAX_LENGTH)
  private String content;

}
