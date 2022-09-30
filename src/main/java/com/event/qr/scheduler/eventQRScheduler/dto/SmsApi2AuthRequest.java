package com.event.qr.scheduler.eventQRScheduler.dto;

public class SmsApi2AuthRequest {

    private String u_name;
    private String passwd;

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "SmsApi2AuthRequest{" +
                "u_name='" + u_name + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
