package com.ptjcoding.nbcampspringnewsfeed.domain.comment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long commentId;

  @Column(nullable = false, length = 500)
  private String content;

  @Column(nullable = false)
  private Long userId;

  @Column(nullable = false)
  private Long postId;

  private Long parentCommentId;

}
