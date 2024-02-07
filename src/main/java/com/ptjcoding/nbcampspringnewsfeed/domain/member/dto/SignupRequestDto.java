package com.ptjcoding.nbcampspringnewsfeed.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequestDto {
	@NotBlank(message = "email은 공백일 수 없음")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "해당 이메일 표현식이 아닙니다.")
	private String email;

	@NotBlank(message = "nickname은 공백일 수 없습니다.")
	@Pattern(regexp = "^(?![^\\s]+[\\s\\W]+$)(?!\\W*[\\s\\W]*$).+$", message = "첫번째나 마지막에 공백문자 및 기호로 끝나지 않습니다.")
	@Size(min=2,max = 15, message = "nickname은 2자 이상 15자 이하만 가능")
	private String nickname;

	@NotBlank(message = "password는 공백일 수 없습니다.")
	@Pattern(regexp = "^(?!.+ ).+$", message = "paswword는 공백을 포함하면 안됨")
	@Pattern(regexp = "^(?=.*\\d).+$", message = "paswword는 숫자를 포함해야 함")
	@Pattern(regexp = "^(?=.*[a-z]).+$", message = "paswword는 소문자를 포함해야 함")
	@Pattern(regexp = "^(?=.*[A-Z]).+$", message = "paswword는 대문자를 포함해야 함")
	private String password;

	@NotBlank(message = "password는 공백일 수 없습니다.")
	@Pattern(regexp = "^(?!.+ ).+$", message = "paswword는 공백을 포함하면 안됨")
	@Pattern(regexp = "^(?=.*\\d).+$", message = "paswword는 숫자를 포함해야 함")
	@Pattern(regexp = "^(?=.*[a-z]).+$", message = "paswword는 소문자를 포함해야 함")
	@Pattern(regexp = "^(?=.*[A-Z]).+$", message = "paswword는 대문자를 포함해야 함")
	private String checkPassword;
}
