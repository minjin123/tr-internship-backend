package springbook.tr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springbook.tr.service.healthCheckResponse;

@RestController
@RequestMapping("/v1/api")
public class healthCheckController {

  @GetMapping("/healthCheck")
  public healthCheckResponse healthCheck(){

    return new healthCheckResponse("UP");
  }
}
