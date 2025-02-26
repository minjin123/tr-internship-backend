package springbook.tr.http.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
	NOT_ISSUE(0, "OK");


	private final int subCode;
	private final String message;
}
