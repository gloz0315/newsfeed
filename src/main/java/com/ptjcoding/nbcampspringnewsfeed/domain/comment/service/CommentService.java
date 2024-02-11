package com.ptjcoding.nbcampspringnewsfeed.domain.comment.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentCreateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import java.util.List;

public interface CommentService {

  Comment createComment(Member member, CommentCreateRequestDto requestDto);

  Comment getCommentByCommentId(Long commentId);

  List<Comment> getCommentsByPostId(Long postId);

  List<Comment> getCommentsByMemberIdAndPostId(Long memberId, Long postId);

  Comment updateComment(Member member, Long commentId, CommentUpdateRequestDto requestDto);

  void deleteComment(Member member, Long commentId);

}
