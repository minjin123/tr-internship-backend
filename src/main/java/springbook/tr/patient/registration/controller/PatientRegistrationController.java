package springbook.tr.patient.registration.controller;

import static springbook.tr.http.model.ResponseCode.*;

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

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import springbook.tr.exception.GlobalExceptionHandler;
import springbook.tr.http.model.HttpResponseBody;
import springbook.tr.patient.find.model.dto.IndividualPatientResponseDto;
import springbook.tr.patient.registration.model.dto.RegistrationRequestDto;
import springbook.tr.patient.registration.model.dto.RegistrationResponseDto;
import springbook.tr.patient.registration.service.PatientRegistrationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "환자 등록", description = "환자의 정보를 기입하는 API")
public class PatientRegistrationController {
	private final PatientRegistrationService patientRegistrationService;

	@Operation(summary = "환자 등록", description = "환자 등록이 정상적으로 작동하는지 확인합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = IndividualPatientResponseDto.class))),
		@ApiResponse(responseCode = "500", description = "응답값 중 null 이 있거나 userId가 존재하지않는 경우", content = @Content(schema = @Schema(implementation = GlobalExceptionHandler.class)))
	})
	@PostMapping("/patients")
	public ResponseEntity<Object> register(@RequestBody RegistrationRequestDto registrationRequestDto) {
		RegistrationResponseDto registrationResponseDto = patientRegistrationService.register(registrationRequestDto);
		return HttpResponseBody.builder()
			.code(HttpStatus.CREATED.value())
			.subCode(NOT_ISSUE.getSubCode())
			.message(NOT_ISSUE.getMessage())
			.response(registrationResponseDto)
			.build();
	}

}
