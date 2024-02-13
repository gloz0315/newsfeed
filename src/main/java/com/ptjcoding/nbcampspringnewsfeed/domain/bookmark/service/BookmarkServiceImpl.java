package com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.dto.BookmarkResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.model.Bookmark;
import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.repository.BookmarkRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.MemberRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.repository.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {

  private final MemberRepository memberRepository;
  private final PostRepository postRepository;
  private final BookmarkRepository bookmarkRepository;

  @Override
  public BookmarkResponseDto createBookmark(Long postId, Long memberId) {
    Member member = memberRepository.findMemberOrElseThrow(memberId);
    Post post = postRepository.findPostOrElseThrow(postId);
    Bookmark bookmark = bookmarkRepository.findBookmarkByPostIdAndMemberIdOrElseThrow(
        post.getPostId(), member.getId());
    return BookmarkResponseDto.from(bookmarkRepository.createBookmark(postId, member.getId())
        , post.getTitle());
  }

  @Override
  @Transactional(readOnly = true)
  public List<BookmarkResponseDto> getBookmarksByMemberId(Long memberId) {
    Member member = memberRepository.findMemberOrElseThrow(memberId);
    return bookmarkRepository.findBookmarksByMemberId(member.getId())
        .stream()
        .map(bookmark -> {
              Post post = postRepository.findPostOrElseThrow(bookmark.getPostId());
              return BookmarkResponseDto.from(bookmark, post.getTitle());
            }
        )
        .toList();
  }

  @Override
  public void deleteBookmark(Long postId, Long memberId) {
    Member member = memberRepository.findMemberOrElseThrow(memberId);
    Post post = postRepository.findPostOrElseThrow(postId);
    bookmarkRepository.deleteBookmark(postId, memberId);
  }
}
