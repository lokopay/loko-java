package io.lokopay.exception;

public class ApiConnectionException extends LokoException {
  private static final long serialVersionUID = 2L;

  public ApiConnectionException(String message) {
    this(message, null);
  }

  public ApiConnectionException(String message, Throwable e) {
    super(message, null, 0, e);
  }
}
