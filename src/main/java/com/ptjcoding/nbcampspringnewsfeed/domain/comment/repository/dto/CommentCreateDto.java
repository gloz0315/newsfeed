package com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentCreateDto {

  private final String content;

  private final Long memberId;
  private final Long postId;
  private final Long parentCommentId;

}
