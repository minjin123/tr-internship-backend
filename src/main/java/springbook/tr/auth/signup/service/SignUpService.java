package springbook.tr.auth.signup.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springbook.tr.auth.signup.exception.PasswordMismatchException;
import springbook.tr.auth.signup.exception.UserAlreadyExistsException;
import springbook.tr.auth.signup.model.SignUpRequestDTO;
import springbook.tr.auth.signup.model.SignUpResponseDTO;
import springbook.tr.user.model.entity.User;
import springbook.tr.user.model.repository.UserRepository;

@Service
public class SignUpService {

  private final UserRepository userRepository;

  public SignUpService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  // 회원 가입
  @Transactional
  public SignUpResponseDTO signUp(SignUpRequestDTO signUpRequestDTO) {


    validSignUp(signUpRequestDTO);

    User user = signUpRequestDTO.toEntity();
    user.passwordEncoder(signUpRequestDTO.getPassword1());
    userRepository.save(user);

    return new SignUpResponseDTO(user.getId());
  }

  // 유효 검증 메서드
  private void validSignUp(SignUpRequestDTO signUpRequestDTO){

    // password1 과 password 2가 맞는지
    if(!signUpRequestDTO.getPassword1().equals(signUpRequestDTO.getPassword2())){
      throw new PasswordMismatchException("Passwords do not match");
    }
    // 유저가 존재 하는지
    if(userRepository.findByUsername(signUpRequestDTO.getUsername()).isPresent()) {
      throw new UserAlreadyExistsException("Username already exists");
    }
  }
}
