package springbook.tr.patient.update.controller;

import static springbook.tr.http.model.ResponseCode.NOT_ISSUE;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import springbook.tr.http.model.HttpResponseBody;
import springbook.tr.patient.update.model.dto.InfoUpdateRequestDto;
import springbook.tr.patient.update.model.dto.InfoUpdateResponseDto;
import springbook.tr.patient.update.service.InfoUpdateService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patients")
@Tag(name = "환자 정보 업데이트", description = "당일 몸무게, 키로 변경하는 API")
public class  InfoUpdateController {

	private final InfoUpdateService infoUpdateService;

	@Operation(summary = "환자 몸무게, 키 업데이트", description = "환자의 몸무게, 키 업데이트가 정상적으로 작동하는지 확인합니다.")
	@ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = InfoUpdateResponseDto.class)))
	@PatchMapping("/{patientId}")
	public ResponseEntity<Object> update(@RequestBody InfoUpdateRequestDto infoUpdateRequestDto) {
		InfoUpdateResponseDto infoUpdateResponseDto = infoUpdateService.update(infoUpdateRequestDto);

		return HttpResponseBody.builder()
			.code(HttpStatus.CREATED.value())
			.subCode(NOT_ISSUE.getSubCode())
			.message(NOT_ISSUE.getMessage())
			.response(infoUpdateResponseDto)
			.build();
	}
}
