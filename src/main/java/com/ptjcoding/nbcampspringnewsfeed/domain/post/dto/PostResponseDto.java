package com.ptjcoding.nbcampspringnewsfeed.domain.post.dto;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class PostResponseDto {

  private final String nickname;
  private final String title;
  private final String content;
  private final Long agreeCount;
  private final Long disagreeCount;
  private final LocalDateTime createdDate;
  private final LocalDateTime updatedDate;
  private final LocalDateTime deleteDate;

  public PostResponseDto(Post post) {
    this.nickname = post.getNickname();
    this.title = post.getTitle();
    this.content = post.getContent();
    this.agreeCount = post.getAgreeCount();
    this.disagreeCount = post.getDisagreeCount();
    this.createdDate = post.getCreatedDate();
    this.updatedDate = post.getUpdatedDate();
    this.deleteDate = post.getDeletedDate();
  }

  public static ResponseEntity<CommonResponseDto<PostResponseDto>> from(PostResponseDto postResponseDto) {
    return CommonResponseDto.ok("게시글 작성 성공", postResponseDto);
  }
}
