package com.winter.nav_eatwhat.data.bean;

public class BaseResponse<T> {
    public String errorMsg;
    public int errorCode;
    public T data;
}
