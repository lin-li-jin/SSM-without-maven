package com.talent.exam.modules.admin_mng.model;

/**
 * 错误信息返回格式定制
 * Created by 吴樟 on www.haixiangzhene.xyz
 * 2017/9/11.
 */
public class ExamResult<T> {

    private String error;   //错误信息
    private Integer code;    //错误编码
    private T message;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
