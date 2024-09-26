package com.generate.exception;

/**
 * 提供的题目不合理异常
 */
public class ProblemException extends Exception {
    public ProblemException(String msg) {
        super((msg));
    }
}
