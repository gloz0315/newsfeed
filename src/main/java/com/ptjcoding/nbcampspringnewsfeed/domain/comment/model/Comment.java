package com.ptjcoding.nbcampspringnewsfeed.domain.comment.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

  private Long commentId;

  private String content;

  private Long memberId;

  private Long postId;

  private Long parentCommentId;

  private LocalDateTime createdDate;

  private LocalDateTime updatedDate;

}

