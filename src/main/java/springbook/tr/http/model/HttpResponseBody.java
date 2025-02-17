package springbook.tr.http.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponseBody<T>{
  private Integer code;
  private Integer subCode;
  private String message;
  private T response;

  public static <T> HttpResponseBody.HttpResponseBodyBuilder<T> builder() {
    return new HttpResponseBody.HttpResponseBodyBuilder<>();
  }

  public static class HttpResponseBodyBuilder<T> {

    private Integer code;

    private Integer subCode;

    private String message;

    private T response;

    HttpResponseBodyBuilder() {
    }

    public HttpResponseBody.HttpResponseBodyBuilder<T> code(final Integer code) {
      this.code = code;
      return this;
    }

    public HttpResponseBody.HttpResponseBodyBuilder<T> subCode(final Integer subCode) {
      this.subCode = subCode;
      return this;
    }

    public HttpResponseBody.HttpResponseBodyBuilder<T> message(final String message) {
      this.message = message;
      return this;
    }

    public HttpResponseBody.HttpResponseBodyBuilder<T> response(final T response) {
      this.response = response;
      return this;
    }
    public ResponseEntity<Object> build(){
      return ResponseEntity
              .status(code)
              .body(new HttpResponseBody<>(this.code, this.subCode,this.message,this.response));
    }
  }
}
