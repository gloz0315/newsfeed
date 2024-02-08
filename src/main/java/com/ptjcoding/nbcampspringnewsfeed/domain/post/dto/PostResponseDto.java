package com.ptjcoding.nbcampspringnewsfeed.domain.post.dto;


import com.ptjcoding.nbcampspringnewsfeed.domain.post.entity.Post;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PostResponseDto {

  private final String nickname;
  private final String title;
  private final String content;
  private final Long agreeCount;
  private final Long disagreeCount;
  private final LocalDateTime created_date;
  private final LocalDateTime updated_date;

  public PostResponseDto(Post post, String nickname) {
    this.nickname = nickname;
    this.title = post.getTitle();
    this.content = post.getContent();
    this.agreeCount = post.getAgreeCount();
    this.disagreeCount = post.getDisagreeCount();
    this.created_date = post.getCreatedDate();
    this.updated_date = post.getUpdatedDate();
  }
}
