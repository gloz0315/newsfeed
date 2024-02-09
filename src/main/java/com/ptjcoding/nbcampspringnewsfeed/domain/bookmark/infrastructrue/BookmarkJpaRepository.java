package com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.infrastructrue;

import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.infrastructrue.Entity.BookmarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkJpaRepository extends JpaRepository<BookmarkEntity, Long> {

}
