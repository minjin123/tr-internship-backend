package springbook.tr.auth.login.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class LoginRequestDto {
	private final String username;
	private final String password;
}
