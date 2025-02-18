package springbook.tr.auth.login.controller;

// 외부 라이브러리

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 내부 라이브러리 (비정적 import)
import springbook.tr.http.model.HttpResponseBody;

import springbook.tr.auth.login.exception.UserNotFoundException;
import springbook.tr.auth.login.model.LoginRequestDto;
import springbook.tr.auth.login.model.LoginResponseDto;
import springbook.tr.auth.login.service.LoginService;

// 내부 라이브러리 (정적 import)
import static springbook.tr.http.model.ResponseCode.NOT_FOUND;
import static springbook.tr.http.model.ResponseCode.NOT_ISSUE;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

	private final LoginService loginService;

	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@PostMapping("/login")
	public ResponseEntity<Object> logIn(@RequestBody LoginRequestDto loginRequest) {
		LoginResponseDto loginResponseDto = loginService.logIn(loginRequest);
		return HttpResponseBody.builder()
			.code(HttpStatus.OK.value())
			.subCode(NOT_ISSUE.getSubCode())
			.message(NOT_ISSUE.getMessage())
			.response(loginResponseDto)
			.build();
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFound() {
		return HttpResponseBody.builder()
			.code(HttpStatus.NOT_FOUND.value())
			.subCode(NOT_FOUND.getSubCode())
			.message(NOT_FOUND.getMessage())
			.response(null)
			.build();
	}
}
