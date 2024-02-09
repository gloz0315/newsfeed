package com.ptjcoding.nbcampspringnewsfeed.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseDto {

  private final String nickname;
  private final String title;
  private final String content;
  private final Long agreeCount;
  private final Long disagreeCount;
  private final LocalDateTime createdDate;
  private final LocalDateTime updatedDate;
  private final LocalDateTime deletedDate;

  public PostResponseDto(Post post) {
    this.nickname = post.getNickname();
    this.title = post.getTitle();
    this.content = post.getContent();
    this.agreeCount = post.getAgreeCount();
    this.disagreeCount = post.getDisagreeCount();
    this.createdDate = post.getCreatedDate();
    this.updatedDate = post.getUpdatedDate();
    this.deletedDate = post.getDeletedDate();
  }
}
