package com.pisces.android.framworkerlibrary.net;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jam on 2017/2/17 下午5:31.
 * Describe:
 */

public class ApiModel<T> {

    public String error;

    public String message;

    @SerializedName("succ")
    public boolean success;

    public T data;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 0 - success
     * 1 - fail
     */
    @SerializedName("stateCode")
    public int errorCode;


}
