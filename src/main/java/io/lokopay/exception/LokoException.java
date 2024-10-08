package io.lokopay.exception;

import io.lokopay.model.LokoError;
import lombok.Getter;
import lombok.Setter;

@Getter
public class LokoException extends Exception{

    private static final long serialVersionUID = 2L;

    /** The error resource returned by Loko's API that caused the exception. */
    // transient so the exception can be serialized, as LokoObject does not
    // implement Serializable
    @Setter transient LokoError lokoError;

    /**
     * Returns the error code of the response that triggered this exception. For {@link ApiException}
     * the error code will be equal to {@link LokoError#getCode()}.
     *
     * @return the string representation of the error code.
     */
    private String code;

    /**
     * Returns the request ID of the request that triggered this exception.
     *
     * @return the request ID.
     */
    private String requestId;

    /**
     * Returns the status code of the response that triggered this exception.
     *
     * @return the status code.
     */
    private Integer statusCode;

    public LokoException(
            String message,
//            String requestId,
            String code, Integer statusCode) {
        this(message, code, statusCode, null);
//                requestId,
    }

    /** Constructs a new Loko exception with the specified details. */
    protected LokoException(
            String message,
//            String requestId,
            String code, Integer statusCode, Throwable e) {
        super(message, e);
        this.code = code;
//        this.requestId = requestId;
        this.statusCode = statusCode;
    }

    /**
     * Returns a description of the exception, including the HTTP status code and request ID (if
     * applicable).
     *
     * @return a string representation of the exception.
     */
    @Override
    public String getMessage() {
        String additionalInfo = "";
//        if (code != null) {
//            additionalInfo += "; code: " + code;
//        }
//        if (requestId != null) {
//            additionalInfo += "; request-id: " + requestId;
//        }
        return super.getMessage() + additionalInfo;
    }

    /**
     * Returns a description of the user facing exception
     *
     * @return a string representation of the user facing exception.
     */
    public String getUserMessage() {
        return super.getMessage();
    }

//    public String getCode() {
//        return this.lokoError.getCodeName();
//    }
}
