package springbook.tr.patient.update.controller;

import static springbook.tr.http.model.ResponseCode.NOT_ISSUE;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import springbook.tr.http.model.HttpResponseBody;
import springbook.tr.patient.update.model.dto.InfoUpdateRequestDto;
import springbook.tr.patient.update.model.dto.InfoUpdateResponseDto;
import springbook.tr.patient.update.service.InfoUpdateService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patients")
public class  InfoUpdateController {

	private final InfoUpdateService infoUpdateService;

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
