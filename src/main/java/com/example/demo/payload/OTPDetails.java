package com.example.demo.payload;

public class OTPDetails {

    private final String otp;
    private final long timeStamp;

    public OTPDetails(String otp, long timeStamp){
        this.otp= otp;
        this.timeStamp=timeStamp;
    }
    public String getOtp(){
        return otp;
    }
    public Long getTimeStamp(){
        return timeStamp;
    }
}
