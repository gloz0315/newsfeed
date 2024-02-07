package com.ptjcoding.nbcampspringnewsfeed.domain.post.entity;


import com.ptjcoding.nbcampspringnewsfeed.domain.common.entity.Timestamped;
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
public class Post extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long postId;
  @Column(nullable = false, length = 40)
  private String title;
  @Column(nullable = false, length = 15)
  private String nickname;
  @Column(nullable = false, length = 500)
  private String content;
  @Column(nullable = false)
  private Long agreeCount;
  @Column(nullable = false)
  private Long disagreeCount;
}
