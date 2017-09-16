package com.pisces.android.wuha.entity.bean;

import java.util.List;

/**
 * Created by Jam on 2017/9/14.
 */

public class LoginResponse {


    /**
     * Id : 4
     * IdentityToken : 7791C6E0CE0B9CAC8F2F1CD6CCE594CE3ADA5AB0F3F219912A4821A79494C970393B90585E750D7B71A03CBE049E4DEA20BBC7A931E1F656A45F1BF4A16AAEAF452936E0A142F4EE84CCC1EF2B3F4B008C3E0140227CDAFB8BAF1BB35AD57AA3E48D2B3AB96508575E41C910D74F8D2D66FE92F1936462BA1159E534ACC2BB1DB61F765482B243FE3CE08FB1C80058AB8029AFCBE738F887A46089E54C8FA44945BFAAC743A5C84D966A2A29AADE78D8E98020ABCD55318A6841C4B7F17BB710578039833DFD865C9F16207A86C4E461
     * MobliePhoneNumber : 17602881290
     * AccountBindDevices : []
     */

    private int Id;
    private String IdentityToken;
    private String MobliePhoneNumber;

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


}
