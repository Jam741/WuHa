package com.pisces.android.framworkerlibrary.net;


/**
 * Created by Jam on 2017/2/17 下午5:31.
 * Describe:
 */


public class ApiModel<T> {

    private T datas;
    private int errorCode;
    private String message;
    private String debugMessage;

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }


    //    public int errorCode = 0;
//    public String message = "SUCCESS";
//    public String debugMessage = "NONE";
}
