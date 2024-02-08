package com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.entity;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentCreateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentUpdateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.common.entity.Timestamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
@Entity
@SQLDelete(sql = "update comment set deleted_date = NOW() where id = ?")
@SQLRestriction(value = "deleted_date is NULL")
public class CommentEntity extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long commentId;

  @Column(nullable = false, length = 500)
  private String content;

  @Column(nullable = false)
  private Long memberId;

  @Column(nullable = false)
  private Long postId;

  private Long parentCommentId;

  public static CommentEntity of(CommentCreateDto createDto) {
    return CommentEntity.builder()
        .content(createDto.getContent())
        .memberId(createDto.getMemberId())
        .postId(createDto.getPostId())
        .parentCommentId(createDto.getParentCommentId())
        .build();
  }

  public void update(CommentUpdateDto updateDto) {
    this.content = updateDto.getContent();
  }

  public Comment toModel() {
    return Comment.builder()
        .commentId(this.commentId)
        .content(this.content)
        .memberId(this.memberId)
        .postId(this.postId)
        .parentCommentId(this.parentCommentId)
        .build();
  }

}
