package springbook.tr.auth.signup.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbook.tr.auth.signup.exception.PasswordMismatchException;
import springbook.tr.auth.signup.exception.UserAlreadyExistsException;
import springbook.tr.auth.signup.model.SignUpRequestDTO;
import springbook.tr.auth.signup.model.SignUpResponseDTO;
import springbook.tr.auth.signup.service.SignUpService;
import springbook.tr.http.model.HttpResponseBody;

import static springbook.tr.http.model.ResponseCode.*;

@RestController
@RequestMapping("/api/v1")
public class SignUpController {

  private final SignUpService signUpService;

  public SignUpController(SignUpService signUpService) {
    this.signUpService = signUpService;
  }
  @PostMapping("/users")
  public ResponseEntity<Object> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
    SignUpResponseDTO signUpResponseDTO = signUpService.signUP(signUpRequestDTO);

    return HttpResponseBody.builder()
            .code(HttpStatus.OK.value())
            .subCode(NOT_ISSUE.getSubCode())
            .message(NOT_ISSUE.getMessage())
            .response(signUpResponseDTO)
            .build();
  }
  @ExceptionHandler(PasswordMismatchException.class)
  public ResponseEntity<Object> handlePasswordMismatch(){
    return HttpResponseBody.builder()
            .code(HttpStatus.BAD_REQUEST.value())
            .subCode(EXITS_USER.getSubCode())
            .message(EXITS_USER.getMessage())
            .response(null)
            .build();
  }
  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<Object> handleUserAlreadyExists(){
    return HttpResponseBody.builder()
            .code(HttpStatus.BAD_REQUEST.value())
            .subCode(NOT_MATCH.getSubCode())
            .message(NOT_MATCH.getMessage())
            .response(null)
            .build();
  }
}
