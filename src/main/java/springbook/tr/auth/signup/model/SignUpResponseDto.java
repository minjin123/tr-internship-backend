package springbook.tr.auth.signup.model;

import lombok.Getter;

@Getter
public class SignUpResponseDto {

	private final Long userId;

	public SignUpResponseDto(Long userId) {
		this.userId = userId;
	}

}
