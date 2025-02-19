package springbook.tr.auth.signup.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springbook.tr.user.model.entity.User;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequestDto {

	private String username;
	private String password;
	private String confirmPassword;
	public User toEntity() {
		return new User(username, password);
	}

}
