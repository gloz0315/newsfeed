package com.ptjcoding.nbcampspringnewsfeed.domain.post.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

  private String nickname;
  private String title;
  private String content;
  private Long agreeCount;
  private Long disagreeCount;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  private LocalDateTime deletedDate;
}
