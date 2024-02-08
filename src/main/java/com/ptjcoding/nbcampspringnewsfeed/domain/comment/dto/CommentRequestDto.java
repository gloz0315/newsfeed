package com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
public class CommentRequestDto implements Serializable {

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
