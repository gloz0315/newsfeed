package com.ptjcoding.nbcampspringnewsfeed.domain.member.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
  Optional<Member> findByEmail(String email);
}
