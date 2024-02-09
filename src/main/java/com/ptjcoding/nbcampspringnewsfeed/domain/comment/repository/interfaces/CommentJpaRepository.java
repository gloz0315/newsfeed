package com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.entity.CommentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {

  @Query("select c from CommentEntity c where c.postId = ?1")
  List<CommentEntity> findAllOrderByPostId();

  @Query("select c from CommentEntity c order by c.postId")
  List<CommentEntity> findAllByOrderByPostIdDesc();
}
