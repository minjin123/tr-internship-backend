package springbook.tr.patient.registration.controller;

import static springbook.tr.http.model.ResponseCode.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import springbook.tr.http.model.HttpResponseBody;
import springbook.tr.patient.registration.model.dto.RegistrationRequestDto;
import springbook.tr.patient.registration.model.dto.RegistrationResponseDto;
import springbook.tr.patient.registration.service.PatientRegistrationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PatientRegistrationController {
	private final PatientRegistrationService patientRegistrationService;

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
