package com.ptjcoding.nbcampspringnewsfeed.domain.post.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.infrastructure.entity.PostEntity;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import java.util.List;

public interface PostRepository {

  Post createPost(PostRequestDto postRequestDto, Member member);

  List<Post> getPosts();

  Post updatePost(Long postId, PostRequestDto postRequestDto);

  PostEntity findByIdOrElseThrow(Long postId);

  Post getPostByPostId(Long postId, Member member, List<Comment> commentList);
}
