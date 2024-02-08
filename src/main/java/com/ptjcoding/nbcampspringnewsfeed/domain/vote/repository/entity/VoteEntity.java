package com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.entity;

import com.ptjcoding.nbcampspringnewsfeed.domain.vote.model.Vote;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto.VoteCreateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto.VoteUpdateDto;
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
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vote")
@Entity
@SQLDelete(sql = "update vote set deleted_date = NOW() where id = ?")
@SQLRestriction(value = "deleted_date is NULL")
@EntityListeners(AuditingEntityListener.class)
public class VoteEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long voteId;

  @Column(nullable = false)
  private Long postId;

  @Column(nullable = false)
  private Boolean isAgree;

  @LastModifiedDate
  @Column(nullable = false)
  private LocalDateTime votedDate;

  private LocalDateTime deletedDate;

  public static VoteEntity of(VoteCreateDto createDto) {
    return VoteEntity.builder()
        .postId(createDto.getPostId())
        .isAgree(createDto.getIsAgree())
        .build();
  }

  public Vote toModel() {
    return new Vote(voteId, postId, isAgree, votedDate);
  }

  public void update(VoteUpdateDto updateDto) {
    this.isAgree = updateDto.getIsAgree();
  }

}
