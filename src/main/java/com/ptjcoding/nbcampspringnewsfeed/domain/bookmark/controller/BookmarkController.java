package com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.controller;


import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.dto.BookmarkResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.service.BookmarkService;
import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

  private final BookmarkService bookmarkService;

  @PostMapping("/register/posts/{postId}")
  public ResponseEntity<CommonResponseDto<BookmarkResponseDto>> createBookmark(
      @PathVariable Long postId, @RequestAttribute("member") Member member
  ) {
    return CommonResponseDto.ok("북마크 등록 성공",
        bookmarkService.createBookmark(postId, member.getId())
    );
  }

  @GetMapping
  public ResponseEntity<CommonResponseDto<List<BookmarkResponseDto>>> getBookmarks(
      @RequestAttribute("member") Member member
  ) {
    return CommonResponseDto.ok("북마크 조회 성공",
        bookmarkService.getBookmarks(member.getId()));
  }

  @DeleteMapping("/deregister/posts/{postId}")
  public ResponseEntity<CommonResponseDto<BookmarkResponseDto>> deleteBookmark(
      @PathVariable Long postId, @RequestAttribute("member") Member member
  ) {
    bookmarkService.deleteBookmark(postId, member.getId());
    return CommonResponseDto.ok("북마크 삭제 성공", null);
  }
}
