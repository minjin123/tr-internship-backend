package springbook.tr.healthcheck.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springbook.tr.healthcheck.model.HealthCheckResponse;
import springbook.tr.healthcheck.model.HealthStatus;


@RestController
@RequestMapping("/v1/api")
public class HealthCheckController {


  @GetMapping("/healthCheck")
  public ResponseEntity<HealthCheckResponse> healthCheck(){

    return ResponseEntity.ok(new HealthCheckResponse(HealthStatus.UP));
  }
}
