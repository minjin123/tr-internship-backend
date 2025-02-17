package springbook.tr.auth.signup.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import springbook.tr.user.model.entity.User;

@Getter
public class SignUpRequestDTO {

  private String username;
  private String password1;
  private String password2;

  @Builder
  private SignUpRequestDTO(String username, String password1, String password2) {
    this.username = username;
    this.password1 = password1;
    this.password2 = password2;
  }

  public User toEntity() {
    return new User(username, password1);
  }

}
