package com.talent.exam.exception;

/**
 * Created by ���� on www.haixiangzhene.xyz
 * 2017/9/18.
 */
public class IllegaleLengthException extends RuntimeException{

    public IllegaleLengthException() {
        super();
    }

    public IllegaleLengthException(String message) {
        super(message);
    }

    public IllegaleLengthException(String message, Throwable cause) {
        super(message, cause);
    }
}
