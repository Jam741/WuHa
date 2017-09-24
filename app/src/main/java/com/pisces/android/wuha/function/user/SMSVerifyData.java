package com.pisces.android.wuha.function.user;

/**
 * Created by Jam on 2017/9/17.
 */

public class SMSVerifyData {


    /**
     * status : 468
     * detail : 需要校验的验证码错误
     */

    private int status;
    private String detail;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
