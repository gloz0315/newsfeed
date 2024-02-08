package com.ptjcoding.nbcampspringnewsfeed.domain.post.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
