package springbook.tr.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	USER_NOT_FOND(HttpStatus.NOT_FOUND, 1, "유저를 찾을 수 없습니다."),
	USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, 2, "유저가 이미 존재합니다."),
	PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST,  3, "비밀번호가 일치하지 않습니다."),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,  4, "서버 내부 오류가 발생했습니다.");

	private final HttpStatus status;
	private final int subCode;
	private final String errorMessage;

}
