package springbook.tr.auth.login.service;

import jakarta.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import springbook.tr.auth.login.model.LoginRequestDto;
import springbook.tr.auth.login.model.LoginResponseDto;
import springbook.tr.exception.CustomException;
import springbook.tr.exception.ErrorCode;
import springbook.tr.user.model.entity.User;
import springbook.tr.user.model.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final UserRepository userRepository;
	private final HttpSession session;

	public LoginResponseDto logIn(LoginRequestDto loginRequestDto) {
		User user = validLogin(loginRequestDto);
		session.setAttribute("loggedInUser", user.getId());
		return new LoginResponseDto(session);
	}

	private User validLogin(LoginRequestDto loginRequestDto) {
		User user = findUserByUserName(loginRequestDto.getUsername());
		validatePassword(loginRequestDto.getPassword(), user.getPassword());
		return user;
	}

	private User findUserByUserName(String username) {
		return userRepository.findByUsername(username)
			.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
	}

	private void validatePassword(String password, String encodedPassword) {
		if (!BCrypt.checkpw(password, encodedPassword)) {
			throw new CustomException(ErrorCode.USER_NOT_FOUND);
		}
	}
}
