package io.lokopay.exception;

public class AuthenticationException  extends LokoException {

    private static final long serialVersionUID = 2L;

    public AuthenticationException (
            String message, String code, Integer statusCode) {
        super(message, code, statusCode);
    }
}
