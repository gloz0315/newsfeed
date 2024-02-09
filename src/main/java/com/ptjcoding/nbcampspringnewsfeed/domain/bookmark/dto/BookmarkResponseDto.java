package com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.dto;

import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.model.Bookmark;
import java.time.LocalDateTime;

public class BookmarkResponseDto {

  private final Long bookmarkId;
  private final Long memberId;
  private final Long postId;
  private final LocalDateTime markedDate;

  public BookmarkResponseDto(Bookmark bookmark) {
    this.bookmarkId = bookmark.getBookmarkId();
    this.memberId = bookmark.getMemberId();
    this.postId = bookmark.getPostId();
    this.markedDate = bookmark.getMarkedDate();
  }
}
