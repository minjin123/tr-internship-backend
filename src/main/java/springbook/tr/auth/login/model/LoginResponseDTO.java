package springbook.tr.auth.login.model;

import jakarta.servlet.http.HttpSession;
import lombok.Getter;

@Getter
public class LoginResponseDTO {

  private final String sessionId;


  public LoginResponseDTO(HttpSession session){
    this.sessionId = session.getId();
  }

}
