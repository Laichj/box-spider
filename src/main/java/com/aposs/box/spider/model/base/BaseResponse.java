package com.aposs.box.spider.model.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ailun
 * @date 2021-07-27
 */
@Data
public class BaseResponse<T> implements Serializable {

    private int retCode;
    private String msg;
    private T data;

    private static final int SUCCESS = 0;
    private static final int FAIL = -1;

    public BaseResponse(int retCode, String msg, T data) {
        this.retCode = retCode;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(int retCode, String msg) {
        this.retCode = retCode;
        this.msg = msg;
        this.data = null;
    }

    public BaseResponse() {
        retCode = SUCCESS;
        msg = "success";
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(SUCCESS, "success", data);
    }

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>(SUCCESS, "success");
    }

}
