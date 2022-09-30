package com.event.qr.scheduler.eventQRScheduler.dto;

public class SmsApi2Request {

    private String msisdn; //"94717855481",
    private String mt_port; //"XYZ bank",
    private String channel; //"1",
    private String s_time; //"2022-09-29 06:46:00",
    private String e_time; //"2022-10-29 06:46:00",
    private String msg; //"This is test message",
    private String callback_url;

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getMt_port() {
        return mt_port;
    }

    public void setMt_port(String mt_port) {
        this.mt_port = mt_port;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getS_time() {
        return s_time;
    }

    public void setS_time(String s_time) {
        this.s_time = s_time;
    }

    public String getE_time() {
        return e_time;
    }

    public void setE_time(String e_time) {
        this.e_time = e_time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    @Override
    public String toString() {
        return "SmsApi2Request{" +
                "msisdn='" + msisdn + '\'' +
                ", mt_port='" + mt_port + '\'' +
                ", channel='" + channel + '\'' +
                ", s_time='" + s_time + '\'' +
                ", e_time='" + e_time + '\'' +
                ", msg='" + msg + '\'' +
                ", callback_url='" + callback_url + '\'' +
                '}';
    }
}
