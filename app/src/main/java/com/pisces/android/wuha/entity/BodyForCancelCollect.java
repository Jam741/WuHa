package com.pisces.android.wuha.entity;

/**
 * Created by Chris Li on 2017/9/17.
 */

public class BodyForCancelCollect {


    /**
     * UserId : string
     * ServiceProviderId : string
     */

    private String UserId;
    private String ServiceProviderId;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getServiceProviderId() {
        return ServiceProviderId;
    }

    public void setServiceProviderId(String ServiceProviderId) {
        this.ServiceProviderId = ServiceProviderId;
    }
}
