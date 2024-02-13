package com.ptjcoding.nbcampspringnewsfeed.domain.post.model;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

  private Long postId;
  private Long memberId;
  private String title;
  private String content;
  private Long agreeCount;
  private Long disagreeCount;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  private LocalDateTime deletedDate;
  private LocalDateTime postUpdatedDate;

  public boolean isNotEqualsMemberId(Long memberId) {
    return !Objects.equals(this.memberId, memberId);
  }
}