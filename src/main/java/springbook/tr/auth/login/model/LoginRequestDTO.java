package springbook.tr.auth.login.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginRequestDTO {
  private String username;
  private String password;

  @Builder
  private LoginRequestDTO(String username, String password) {
    this.username = username;
    this.password = password;
  }

}
