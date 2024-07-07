package io.lokopay.exception;

public class InvalidRequestException extends LokoException{

    private static final long serialVersionUID = 2L;

//    private final String params;

    public InvalidRequestException(
            String message,
//            String param,
//            String requestId,
            String code,
            Integer statusCode,
            Throwable e) {
        super(message,
//                requestId,
                code, statusCode, e);
//        this.params = param;
    }
}
