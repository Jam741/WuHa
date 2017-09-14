package com.pisces.android.wuha.entity.bean;

/**
 * Created by Jam on 2017/9/14.
 */

public class LoginResponse {


    /**
     * Id : 0
     * IdentityToken : null
     * MobliePhoneNumber : null
     * AccountBindDevices : null
     */

    private int Id;
    private String IdentityToken;
    private String MobliePhoneNumber;
    private String AccountBindDevices;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getIdentityToken() {
        return IdentityToken;
    }

    public void setIdentityToken(String IdentityToken) {
        this.IdentityToken = IdentityToken;
    }

    public String getMobliePhoneNumber() {
        return MobliePhoneNumber;
    }

    public void setMobliePhoneNumber(String MobliePhoneNumber) {
        this.MobliePhoneNumber = MobliePhoneNumber;
    }

    public String getAccountBindDevices() {
        return AccountBindDevices;
    }

    public void setAccountBindDevices(String AccountBindDevices) {
        this.AccountBindDevices = AccountBindDevices;
    }
}
