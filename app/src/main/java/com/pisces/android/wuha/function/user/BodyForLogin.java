package com.pisces.android.wuha.function.user;

/**
 * Created by Jam on 2017/9/14.
 */

public class BodyForLogin {


    /**
     * IdentityToken : string
     * MobliePhoneNumber : string
     * CurrentDeviceIdentificationNumber : string
     * DeviceName : string
     * VerificationCode": string
     */


    private String IdentityToken;
    private String MobliePhoneNumber;
    private String CurrentDeviceIdentificationNumber;
    private String DeviceName;
    private String VerificationCode;

    public String getVerificationCode() {
        return VerificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        VerificationCode = verificationCode;
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

    public String getCurrentDeviceIdentificationNumber() {
        return CurrentDeviceIdentificationNumber;
    }

    public void setCurrentDeviceIdentificationNumber(String CurrentDeviceIdentificationNumber) {
        this.CurrentDeviceIdentificationNumber = CurrentDeviceIdentificationNumber;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String DeviceName) {
        this.DeviceName = DeviceName;
    }
}
