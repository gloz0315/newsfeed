package com.ptjcoding.nbcampspringnewsfeed.domain.post.entity;


import com.ptjcoding.nbcampspringnewsfeed.domain.common.entity.Timestamped;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long postId;
  @Column(nullable = false)
  private Long memberId;
  @Setter
  @Column(nullable = false, length = 40)
  private String title;
  @Setter
  @Column(nullable = false, length = 500)
  private String content;
  @Column(nullable = false)
  private Long agreeCount;
  @Column(nullable = false)
  private Long disagreeCount;

  public Post(PostRequestDto postRequestDto) {
    this.memberId = postRequestDto.getMemberId();
    this.title = postRequestDto.getTitle();
    this.content = postRequestDto.getContent();
    this.agreeCount = 0L;
    this.disagreeCount = 0L;
  }
}
