package com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto;

import static com.ptjcoding.nbcampspringnewsfeed.domain.comment.etc.CommentRequestError.CommentRequestErrorMessage.CONTENT_BLANK;
import static com.ptjcoding.nbcampspringnewsfeed.domain.comment.etc.CommentRequestError.CommentRequestErrorMessage.CONTENT_MAX_LENGTH;
import static com.ptjcoding.nbcampspringnewsfeed.domain.comment.etc.CommentRequestError.CommentRequestErrorMessage.PARENTCOMMENTIDSTRING_POSITIVE;
import static com.ptjcoding.nbcampspringnewsfeed.domain.comment.etc.CommentRequestError.CommentRequestErrorMessage.POSTID_BLANK;
import static com.ptjcoding.nbcampspringnewsfeed.domain.comment.etc.CommentRequestError.CommentRequestErrorMessage.POSTID_POSITIVE;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class CommentCreateRequestDto {

  @NotNull(message = CONTENT_BLANK)
  @NotBlank(message = CONTENT_BLANK)
  @Length(max = 500, message = CONTENT_MAX_LENGTH)
  private String content;

  @NotNull(message = POSTID_BLANK)
  @Positive(message = POSTID_POSITIVE)
  private Long postId;

  @Positive(message = PARENTCOMMENTIDSTRING_POSITIVE)
  private Long parentCommentId;

}
