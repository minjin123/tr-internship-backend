package springbook.tr.auth.login.model;

import lombok.Getter;

@Getter
public class LoginRequestDto {
	private final String username;
	private final String password;

	// @Builder
	private LoginRequestDto(String username, String password) {
		this.username = username;
		this.password = password;
	}

}
