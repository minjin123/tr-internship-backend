package springbook.tr.auth.signup.model;

import lombok.Getter;

@Getter
public class SignUpResponseDTO{

  private final Long userId;

  public SignUpResponseDTO(Long userId){
    this.userId = userId;
  }

}
