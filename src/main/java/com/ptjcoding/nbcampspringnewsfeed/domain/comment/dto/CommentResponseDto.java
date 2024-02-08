package com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponseDto {

  private final Long commentId;

  private final String content;

  private final Long memberId;

  private final Long postId;

  private final Long parentCommentId;

  private final LocalDateTime createdDate;

  private final LocalDateTime updatedDate;

  public static CommentResponseDto of(Comment comment) {
    return CommentResponseDto.builder()
        .commentId(comment.getCommentId())
        .content(comment.getContent())
        .memberId(comment.getMemberId())
        .postId(comment.getPostId())
        .parentCommentId(comment.getParentCommentId())
        .createdDate(comment.getCreatedDate())
        .updatedDate(comment.getUpdatedDate())
        .build();
  }

}
