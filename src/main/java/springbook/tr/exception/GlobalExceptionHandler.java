package springbook.tr.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import springbook.tr.http.model.HttpResponseBody;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Object> handleCustomException(CustomException e) {
		log.error("{}:{}", e.getErrorCode().name(), e.getMessage());

		return HttpResponseBody.builder()
			.code(e.getErrorCode().getStatus().value())
			.subCode(e.getErrorCode().getSubCode())
			.message(e.getErrorCode().getErrorMessage())
			.response(null)
			.build();
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGeneralException(Exception e) {
		log.error("Exception", e);

		return HttpResponseBody.builder()
			.code(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value())
			.subCode(ErrorCode.INTERNAL_SERVER_ERROR.getSubCode())
			.message(ErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage())
			.response(null)
			.build();
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusinessException(BusinessException e) {
		log.error("{}:{}", e.getErrorCode().name(), e.getMessage());

		return HttpResponseBody.builder()
			.code(e.getErrorCode().getStatus().value())
			.subCode(e.getErrorCode().getSubCode())
			.message(e.getErrorCode().getErrorMessage())
			.response(null)
			.build();
	}


}
