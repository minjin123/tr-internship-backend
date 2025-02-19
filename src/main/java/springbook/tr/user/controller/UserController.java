package springbook.tr.user.controller;

import static springbook.tr.http.model.ResponseCode.*;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import springbook.tr.http.model.HttpResponseBody;
import springbook.tr.patient.find.model.dto.IndividualPatientResponseDto;
import springbook.tr.patient.find.model.dto.PatientListResponseDto;
import springbook.tr.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{userId}")
public class UserController {

	private final UserService userService;


	@Operation(summary = "특정 환자 조회", description = "특정 환자 조회가 정상적으로 작동하는지 확인합니다.")
	@ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = IndividualPatientResponseDto.class)))
	@GetMapping("/patients/{patientId}")
	public ResponseEntity<Object> getPatient(@PathVariable Long userId, @PathVariable Long patientId) {
		IndividualPatientResponseDto individualPatientResponseDto = userService.findPatientByUserId(userId, patientId);
		return HttpResponseBody.builder()
			.code(HttpStatus.OK.value())
			.subCode(NOT_ISSUE.getSubCode())
			.message(NOT_ISSUE.getMessage())
			.response(individualPatientResponseDto)
			.build();
	}

	@Operation(summary = "전체 환자 조회", description = "전체 환자 조회가 정상적으로 작동하는지 확인합니다.")
	@ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = PatientListResponseDto.class)))
	@GetMapping("/patients")
	public ResponseEntity<Object> getPatients(@PathVariable Long userId) {
		List<PatientListResponseDto> patientListResponseDto = userService.findAllPatientsByUserId(userId);
		return HttpResponseBody.builder()
			.code(HttpStatus.OK.value())
			.subCode(NOT_ISSUE.getSubCode())
			.message(NOT_ISSUE.getMessage())
			.response(patientListResponseDto)
			.build();
	}

}
