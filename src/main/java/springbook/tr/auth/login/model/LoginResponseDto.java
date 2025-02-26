package springbook.tr.auth.login.model;

import jakarta.servlet.http.HttpSession;
import lombok.Getter;

@Getter
public class LoginResponseDto {

	private final String sessionId;

	public LoginResponseDto(HttpSession session) {
		this.sessionId = session.getId();
	}

}
