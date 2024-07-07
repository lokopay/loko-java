package io.lokopay.exception;

public class IdempotencyException extends LokoException {
  private static final long serialVersionUID = 2L;

  public IdempotencyException(String message, String code, Integer statusCode) { //String requestId,
    super(message, code, statusCode); //requestId,
  }
}
