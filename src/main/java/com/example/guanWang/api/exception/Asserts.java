package com.example.guanWang.api.exception;

//import com.example.demo.api.IErrorCode;

import com.example.guanWang.api.common.IErrorCode;

/**
 * 断言处理类，用于抛出各种API异常
 * Created by macro on 2020/2/27.
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
