package springbook.tr.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, 1, "유저를 찾을 수 없습니다."),
	PATIENT_NOT_FOUND(HttpStatus.NOT_FOUND, 2, "환자를 찾을 수 없습니다."),
	NOT_FOUND_DATE(HttpStatus.NOT_FOUND, 3, "해당 날짜를 찾을 수 없습니다."),

	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,  1, "서버 내부 오류가 발생했습니다."),

	INVALID_DATA_SIZE(HttpStatus.BAD_REQUEST,  1, "데이터 수가 3개가 아닙니다."),

	USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, 1, "유저가 이미 존재합니다."),
	PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST,  2, "비밀번호가 일치하지 않습니다."),
	NULL_OR_EMPTY(HttpStatus.BAD_REQUEST, 3, "측정이 잘못 되었습니다.");


	private final HttpStatus status;
	private final int subCode;
	private final String errorMessage;

}
