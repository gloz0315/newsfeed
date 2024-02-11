package com.ptjcoding.nbcampspringnewsfeed.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseDto {

  private final String nickname;
  private final String title;
  private final String content;
  private final Long agreeCount;
  private final Long disagreeCount;
  private final List<Comment> commentList;
  private final LocalDateTime createdDate;
  private final LocalDateTime updatedDate;
  private final LocalDateTime deletedDate;

  public static PostResponseDto from(Post post, String nickname, List<Comment> commentList) {
    return PostResponseDto.builder()
        .nickname(nickname)
        .title(post.getTitle())
        .content(post.getContent())
        .agreeCount(post.getAgreeCount())
        .disagreeCount(post.getDisagreeCount())
        .createdDate(post.getCreatedDate())
        .updatedDate(post.getUpdatedDate())
        .deletedDate(post.getDeletedDate())
        .commentList(commentList)
        .build();
  }
}
