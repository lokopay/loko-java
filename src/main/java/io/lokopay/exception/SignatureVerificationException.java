package io.lokopay.exception;

import lombok.Getter;

@Getter
public class SignatureVerificationException extends LokoException {
  private static final long serialVersionUID = 2L;

  private final String sigHeader;

  public SignatureVerificationException(String message, String sigHeader) {
    super(message, null, 0);
    this.sigHeader = sigHeader;
  }
}
