package com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.infrastructrue.BookmarkJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.infrastructrue.Entity.BookmarkEntity;
import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.model.Bookmark;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepository {

  private final BookmarkJpaRepository bookmarkJpaRepository;

  @Override
  public Bookmark createBookmark(Long postId, Long memberId) {
    return bookmarkJpaRepository.save(BookmarkEntity.of(postId, memberId)).toModel();
  }

  @Override
  public List<Bookmark> findBookmarksByMemberId(Long memberId) {
    return bookmarkJpaRepository.findAllByMemberId(memberId)
        .stream()
        .map(BookmarkEntity::toModel)
        .toList();
  }

  @Override
  public void deleteBookmark(Long postId, Long memberId) {
    bookmarkJpaRepository.deleteByPostIdAndMemberId(postId, memberId);
  }

  @Override
  public void deleteBookmarksByPostId(Long postId) {
    bookmarkJpaRepository.deleteByPostId(postId);
  }

  @Override
  public void deleteBookmarksByMemberId(Long memberId) {
    bookmarkJpaRepository.deleteByMemberId(memberId);
  }
}
