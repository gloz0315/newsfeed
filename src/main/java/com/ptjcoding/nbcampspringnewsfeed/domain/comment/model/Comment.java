package com.ptjcoding.nbcampspringnewsfeed.domain.comment.model;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.entity.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends Timestamped {

  private Long commentId;

  private String content;

  private Long memberId;

  private Long postId;

  private Long parentCommentId;

}

