package com.generate.exception;

/**
 * 参数值不合理异常
 */
public class NumberOrLimitException extends Exception {
    public NumberOrLimitException(String message) {
        super(message);
    }
}
