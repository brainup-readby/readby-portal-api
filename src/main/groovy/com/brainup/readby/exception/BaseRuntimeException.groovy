package com.brainup.readby.exception

import org.apache.commons.lang.StringUtils

/**
 * Base exception.
 */
public class BaseRuntimeException extends RuntimeException {

    private String code = StringUtils.EMPTY;

    /**
     * Constructor that takes in a message.
     *
     * @param message
     */
    public BaseRuntimeException(String message) {
        super(message);
    }

    /**
     * Constructor that takes in a message and cause.
     *
     * @param message
     * @param cause
     */
    public BaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor that takes in a message and code.
     *
     * @param message
     * @param code
     *
     */
    public BaseRuntimeException(String message, String code) {
        super(message);
        this.code = code;
    }

    /**
     * Constructor that takes in a message, code, and cause.
     *
     * @param message
     * @param code
     * @param cause
     */
    public BaseRuntimeException(String message, String code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}