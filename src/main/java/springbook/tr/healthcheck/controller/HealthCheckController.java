package springbook.tr.healthcheck.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springbook.tr.healthcheck.model.HealthCheckResponse;
import springbook.tr.healthcheck.service.HealthCheckService;

@RestController
@RequestMapping("/v1/api")
public class HealthCheckController {


  @GetMapping("/healthCheck")
  public ResponseEntity<Object> healthCheck() {
    HealthCheckResponse response = HealthCheckService.getServerHealthStatus();
    return ResponseEntity.ok(response);
  }
}
