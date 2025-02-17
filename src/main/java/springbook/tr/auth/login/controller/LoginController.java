package springbook.tr.auth.login.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbook.tr.auth.login.exception.UserNotFoundException;
import springbook.tr.auth.login.model.LoginRequestDTO;
import springbook.tr.auth.login.model.LoginResponseDTO;
import springbook.tr.auth.login.services.LoginService;
import springbook.tr.http.model.HttpResponseBody;

import static springbook.tr.http.model.ResponseCode.NOT_FOUND;
import static springbook.tr.http.model.ResponseCode.NOT_ISSUE;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {
  private final LoginService logInService;

  public LoginController(LoginService logInService) {
    this.logInService = logInService;
  }
  @PostMapping("/login")
  public ResponseEntity<Object> logIn(@RequestBody LoginRequestDTO loginRequest) {
    LoginResponseDTO LoginResponseDTO = logInService.logIn(loginRequest);
    return HttpResponseBody.builder()
            .code(HttpStatus.OK.value())
            .subCode(NOT_ISSUE.getSubCode())
            .message(NOT_ISSUE.getMessage())
            .response(LoginResponseDTO)
            .build();
  }
  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<Object> handleUserNotFound(){
    return HttpResponseBody.builder()
            .code(HttpStatus.NOT_FOUND.value())
            .subCode(NOT_FOUND.getSubCode())
            .message(NOT_FOUND.getMessage())
            .response(null)
            .build();
  }
}
