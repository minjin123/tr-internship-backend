package springbook.tr.user.controller;

import static springbook.tr.http.model.ResponseCode.*;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springbook.tr.http.model.HttpResponseBody;
import springbook.tr.patient.find.model.dto.IndividualPatientResponseDto;
import springbook.tr.patient.find.model.dto.PatientListResponseDto;
import springbook.tr.user.service.UserService;

@RestController
@RequestMapping("/api/v1/users/{userId}")
public class UserController {

	private final UserService UserService;

	public UserController(UserService UserService) {
		this.UserService = UserService;
	}

	@GetMapping("/patients/{patientId}")
	public ResponseEntity<Object> getPatient(@PathVariable Long userId, @PathVariable Long patientId) {
		IndividualPatientResponseDto individualPatientResponseDTO = UserService.findPatientById(userId, patientId);
		return HttpResponseBody.builder()
			.code(HttpStatus.OK.value())
			.subCode(NOT_ISSUE.getSubCode())
			.message(NOT_ISSUE.getMessage())
			.response(individualPatientResponseDTO)
			.build();
	}

	@GetMapping("/patients")
	public ResponseEntity<Object> getPatients(@PathVariable Long userId) {
		List<PatientListResponseDto> patientListResponseDTO = UserService.findAllPatientsByUserId(userId);
		return HttpResponseBody.builder()
			.code(HttpStatus.OK.value())
			.subCode(NOT_ISSUE.getSubCode())
			.message(NOT_ISSUE.getMessage())
			.response(patientListResponseDTO)
			.build();
	}

}
