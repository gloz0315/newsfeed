package com.ptjcoding.nbcampspringnewsfeed.domain.post.infrastructure.entity;

import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@Table(name = "post")
@SQLDelete(sql = "update post set delete_date = NOW() where id = ?")
@SQLRestriction(value = "delete_date is NULL")
@EntityListeners(AuditingEntityListener.class)
public class PostEntity {

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
  @CreatedDate
  @Column(name = "created_date")
  private LocalDateTime createdDate;
  @UpdateTimestamp
  @Column(name = "updated_date")
  private LocalDateTime updatedDate;
  @Column(name = "deleted_date")
  private LocalDateTime deletedDate;

  public static PostEntity of(PostRequestDto postRequestDto, Long memberId) {
    return PostEntity
        .builder()
        .memberId(memberId)
        .title(postRequestDto.getTitle())
        .content(postRequestDto.getContent())
        .agreeCount(0L)
        .disagreeCount(0L)
        .build();
  }

  public Post toModel(String nickname) {
    return Post
        .builder()
        .nickname(nickname)
        .title(title)
        .content(content)
        .agreeCount(agreeCount)
        .disagreeCount(disagreeCount)
        .createdDate(createdDate)
        .updatedDate(updatedDate)
        .deletedDate(deletedDate)
        .build();
  }
}
