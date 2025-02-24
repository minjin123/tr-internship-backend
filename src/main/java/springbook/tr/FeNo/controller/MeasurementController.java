package springbook.tr.FeNo.controller;

import static springbook.tr.http.model.ResponseCode.*;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import springbook.tr.FeNo.Service.MeasurementRecordInquiryService;
import springbook.tr.FeNo.Service.MeasurementService;
import springbook.tr.FeNo.model.dto.MeasurementInquiryResponseDto;
import springbook.tr.FeNo.model.dto.MeasurementResponseDto;
import springbook.tr.exception.GlobalExceptionHandler;
import springbook.tr.http.model.HttpResponseBody;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
@Tag(name = "검사 등록 및 조회", description = "FeNo 검사 및 결과 조회 API")
public class MeasurementController {

	private final MeasurementService measurementService;
	private final MeasurementRecordInquiryService measurementRecordInquiryService;

	@Operation(summary = "FeNo 검사", description = "FeNo 검사가 정상적으로 작동하는지 확인합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공",
			content = @Content(schema = @Schema(implementation = MeasurementResponseDto.class))),
		@ApiResponse(responseCode = "400", description = "measurements가 NULL 이거나 비어있습니다.",
			content = @Content(schema = @Schema(implementation = GlobalExceptionHandler.class)))
	})
	@PostMapping(value = "/{patientId}/measurements", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> measurement(@RequestParam("file") MultipartFile file,
		@PathVariable Long patientId) {
		MeasurementResponseDto measurementResponseDto = measurementService.processAndSaveMeasurements(file, patientId);

		return HttpResponseBody.builder()
			.code(HttpStatus.CREATED.value())
			.subCode(NOT_ISSUE.getSubCode())
			.message(NOT_ISSUE.getMessage())
			.response(measurementResponseDto)
			.build();
	}

	@Operation(summary = "FeNo 검사결과 조회", description = "FeNo 검사결과 조회가 정상적으로 작동하는지 확인합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공",
			content = @Content(schema = @Schema(implementation = MeasurementInquiryResponseDto.class))),
		@ApiResponse(responseCode = "404", description = "해당 날짜에 검사를 찾을 수 없습니다.",
			content = @Content(schema = @Schema(implementation = GlobalExceptionHandler.class))),
		@ApiResponse(responseCode = "404", description = "환자가 찾을 수 없습니다.",
			content = @Content(schema = @Schema(implementation = GlobalExceptionHandler.class)))
	})
	@GetMapping("/{patientId}/measurements")
	public ResponseEntity<Object> measurementRecordInquiry(@PathVariable Long patientId,
		@RequestParam(required = false) String date) {
		List<MeasurementInquiryResponseDto> measurementInquiryResponseDto = measurementRecordInquiryService
			.getMeasurementRecord(patientId, date);
		return HttpResponseBody.builder()
			.code(HttpStatus.CREATED.value())
			.subCode(NOT_ISSUE.getSubCode())
			.message(NOT_ISSUE.getMessage())
			.response(measurementInquiryResponseDto)
			.build();

	}
}