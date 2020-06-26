package com.brainup.readby.exception

/** Thrown when the input request is invalid or badly formatted */
public class BadRequestException extends BaseRuntimeException {
    /**
     * Constructor that takes in a message.
     *
     * @param message
     */
    public BadRequestException(String message) {
        super(message);
    }

    /**
     * Constructor that takes in a message and cause.
     *
     * @param message
     * @param cause
     */
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor that takes in a message and code.
     *
     * @param message
     * @param code
     */
    public BadRequestException(String message, String code) {
        super(message, code);

    }

    /**
     * Constructor that takes in a message, code and cause.
     *
     * @param message
     * @param code
     * @param cause
     */
    public BadRequestException(String message, String code, Throwable cause) {
        super(message, code, cause);
    }
}
