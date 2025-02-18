package springbook.tr.auth.login.service;

import jakarta.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import springbook.tr.auth.login.exception.UserNotFoundException;
import springbook.tr.auth.login.model.LoginRequestDto;
import springbook.tr.auth.login.model.LoginResponseDto;
import springbook.tr.user.model.entity.User;
import springbook.tr.user.model.repository.UserRepository;

@Service
public class LoginService {

	private final UserRepository userRepository;
	private final HttpSession session;

	public LoginService(UserRepository userRepository, HttpSession session) {
		this.userRepository = userRepository;
		this.session = session;
	}

	// 로그인
	public LoginResponseDto logIn(LoginRequestDto loginRequestDto) {
		User user = validLogin(loginRequestDto);
		session.setAttribute("loggedInUser", user.getId());
		return new LoginResponseDto(session);
	}

	private User validLogin(LoginRequestDto loginRequestDto) {
		User user = userRepository.findByUsername(loginRequestDto.getUsername())
			.orElseThrow(() -> new UserNotFoundException("Username or password is incorrect"));
		if (!isPasswordMatch(loginRequestDto.getPassword(), user.getPassword())) {
			throw new UserNotFoundException("Username or password is incorrect");
		}
		return user;
	}

	private boolean isPasswordMatch(String password, String encodedPassword) {
		return BCrypt.checkpw(password, encodedPassword);
	}
}
