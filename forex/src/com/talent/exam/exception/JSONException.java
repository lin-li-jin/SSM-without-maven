package com.talent.exam.exception;

/**
 * json¥ÌŒÛ
 * Created by Œ‚’¡ on www.haixiangzhene.xyz
 * 2017/9/13.
 */
public class JSONException  extends RuntimeException{

    public JSONException() {
    }

    public JSONException(String message) {
        super(message);
    }

    public JSONException(String message, Throwable cause) {
        super(message, cause);
    }
}
