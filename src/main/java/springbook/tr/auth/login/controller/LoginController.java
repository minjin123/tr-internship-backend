package springbook.tr.auth.login.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import springbook.tr.http.model.HttpResponseBody;

import springbook.tr.auth.login.model.LoginRequestDto;
import springbook.tr.auth.login.model.LoginResponseDto;
import springbook.tr.auth.login.service.LoginService;

import static springbook.tr.http.model.ResponseCode.NOT_ISSUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class LoginController {

	private final LoginService loginService;

	@Operation(summary = "로그인", description = "로그인이 정상적으로 작동하는지 확인합니다.")
	@ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = LoginResponseDto.class)))
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
}
