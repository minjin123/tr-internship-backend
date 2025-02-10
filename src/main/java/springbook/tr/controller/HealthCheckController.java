package springbook.tr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springbook.tr.service.HealthCheckResponse;

@RestController
@RequestMapping("/v1/api")
public class HealthCheckController {

  @GetMapping("/healthCheck")
  public ResponseEntity<HealthCheckResponse> healthCheck(){

    return ResponseEntity.ok(new HealthCheckResponse("UP"));
  }
}
