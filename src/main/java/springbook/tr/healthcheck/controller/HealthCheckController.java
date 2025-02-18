package springbook.tr.healthcheck.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springbook.tr.healthcheck.model.HealthCheckResponse;
import springbook.tr.healthcheck.service.HealthCheckService;

@RestController
@RequestMapping("/v1/api")
public class HealthCheckController {

	@Operation(summary = "서버 상태 헬스체크", description = "서버가 정상적으로 운영중인지 확입합니다.")
	@ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = HealthCheckResponse.class)))
	@GetMapping("/healthCheck")
	public ResponseEntity<Object> healthCheck() {
		HealthCheckResponse response = HealthCheckService.getServerHealthStatus();
		return ResponseEntity.ok(response);
	}
}

