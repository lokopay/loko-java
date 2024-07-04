package io.lokopay.exception;

public class ApiException extends LokoException {
  private static final long serialVersionUID = 2L;

  public ApiException(
      String message, String code, Integer statusCode, Throwable e) {
    super(message, code, statusCode, e);
  }
}
