package springbook.tr.http.model;

import lombok.Getter;


public enum ResponseCode {
  NOT_ISSUE(0,"OK"),
  EXITS_USER(1,"Bad Request"),
  NOT_MATCH(2,"Bad Request");
  private final int subCode;
  private final String message;

  ResponseCode(int subCode, String message){
    this.subCode = subCode;
    this.message = message;
  }
  public int getSubCode(){
    return subCode;
  }
  public String getMessage(){
    return message;
  }
}
