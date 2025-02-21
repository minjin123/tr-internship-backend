package springbook.tr.FeNo.controller;

import static springbook.tr.http.model.ResponseCode.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import springbook.tr.FeNo.Service.MeasurementService;
import springbook.tr.FeNo.model.dto.MeasurementResponseDto;
import springbook.tr.http.model.HttpResponseBody;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class MeasurementController {
	private final MeasurementService measurementService;

	@PostMapping(value = "/{patientId}/measurements", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> measurement(@RequestParam("file") MultipartFile file,
		@RequestParam("patientId") Long patientId) {
		MeasurementResponseDto measurementResponseDto = measurementService.processAndSaveMeasurements(file, patientId);

		return HttpResponseBody.builder()
			.code(HttpStatus.CREATED.value())
			.subCode(NOT_ISSUE.getSubCode())
			.message(NOT_ISSUE.getMessage())
			.response(measurementResponseDto)
			.build();
	}
}
