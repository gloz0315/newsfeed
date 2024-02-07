package com.ptjcoding.nbcampspringnewsfeed.domain.member.entity;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.entity.Timestamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member")
public class Member extends Timestamped {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false,unique = true,length = 40)
	private String email;

	@Column(nullable = false,length = 15)
	private String nickname;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false,length = 10)
	@Enumerated(value = EnumType.STRING)
	private MemberRole role;

	@Builder
	public Member(String email, String nickname, String password, MemberRole role) {
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.role = role;
	}
}
