package springbook.tr.auth.login.services;

import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import springbook.tr.auth.login.exception.UserNotFoundException;
import springbook.tr.auth.login.model.LoginRequestDTO;
import springbook.tr.auth.login.model.LoginResponseDTO;
import springbook.tr.user.model.entity.User;
import springbook.tr.user.model.repository.UserRepository;

import java.util.HashMap;

@Service
public class LoginService {

  private final UserRepository userRepository;
  private final HttpSession session;

  public LoginService(UserRepository userRepository, HttpSession session) {
    this.userRepository = userRepository;
    this.session = session;
  }


  // 로그인
  public LoginResponseDTO logIn(LoginRequestDTO loginRequestDTO) {
    User user = validLogin(loginRequestDTO);
    session.setAttribute("loggedInUser",user.getId());
    return new LoginResponseDTO(session);
  }

  private User validLogin(LoginRequestDTO loginRequestDTO) {
    User user = userRepository.findByUsername(loginRequestDTO.getUsername())
        .orElseThrow(() -> new UserNotFoundException("Username or password is incorrect"));
    if(!isPasswordMatch(loginRequestDTO.getPassword(), user.getPassword())) {
      throw new UserNotFoundException("Username or password is incorrect");
    }
    return user;
  }
  private boolean isPasswordMatch(String password, String encodedPassword) {
    return BCrypt.checkpw(password, encodedPassword);
  }
}
