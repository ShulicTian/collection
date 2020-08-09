package com.data.collection.common.entity;

public class ResponseEntity<T> {

    public static final String OK = "1";
    public static final String ERROR = "-1";

    private String code = ERROR;
    private String msg = "";
    private T result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
