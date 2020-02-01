package com.hackdroid.droidcontacts.Model;

public class Logs {
    String mobile , callType , callDate , callDuration ;

    public Logs(String mobile, String callType, String callDate, String callDuration) {
        this.mobile = mobile;
        this.callType = callType;
        this.callDate = callDate;
        this.callDuration = callDuration;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    public String getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }
}
