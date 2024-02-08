package com.ptjcoding.nbcampspringnewsfeed.domain.post.model;

import com.ptjcoding.nbcampspringnewsfeed.domain.post.infrastructure.entity.PostEntity;
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

  public static Post of(PostEntity postEntity, String nickname) {
    return Post
        .builder()
        .nickname(nickname)
        .title(postEntity.getTitle())
        .content(postEntity.getContent())
        .agreeCount(postEntity.getAgreeCount())
        .disagreeCount(postEntity.getDisagreeCount())
        .createdDate(postEntity.getCreatedDate())
        .updatedDate(postEntity.getUpdatedDate())
        .deletedDate(postEntity.getDeletedDate())
        .build();
  }
}
