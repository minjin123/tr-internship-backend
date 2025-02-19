package springbook.tr.auth.signup.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import springbook.tr.auth.signup.model.SignUpRequestDto;
import springbook.tr.auth.signup.model.SignUpResponseDto;
import springbook.tr.auth.signup.service.SignUpService;
import springbook.tr.http.model.HttpResponseBody;

import static springbook.tr.http.model.ResponseCode.*;

@RestController
@RequestMapping("/api/v1")
public class SignUpController {

	private final SignUpService signUpService;

	public SignUpController(SignUpService signUpService) {
		this.signUpService = signUpService;
	}

	@Operation(summary = "회원가입", description = "회원가입이 정상적으로 작동하는지 확인합니다.")
	@ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = SignUpResponseDto.class)))
	@PostMapping("/users")
	public ResponseEntity<Object> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
		SignUpResponseDto signUpResponseDto = signUpService.signUp(signUpRequestDto);

		return HttpResponseBody.builder()
			.code(HttpStatus.CREATED.value())
			.subCode(NOT_ISSUE.getSubCode())
			.message(NOT_ISSUE.getMessage())
			.response(signUpResponseDto)
			.build();
	}
}

