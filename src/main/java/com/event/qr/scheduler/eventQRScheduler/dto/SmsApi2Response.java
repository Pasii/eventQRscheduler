package com.event.qr.scheduler.eventQRScheduler.dto;

public class SmsApi2Response {

    private String error;
    private String camp_id;
    private String ref_id;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCamp_id() {
        return camp_id;
    }

    public void setCamp_id(String camp_id) {
        this.camp_id = camp_id;
    }

    public String getRef_id() {
        return ref_id;
    }

    public void setRef_id(String ref_id) {
        this.ref_id = ref_id;
    }

    @Override
    public String toString() {
        return "SmsApi2Response{" +
                "error='" + error + '\'' +
                ", camp_id='" + camp_id + '\'' +
                ", ref_id='" + ref_id + '\'' +
                '}';
    }
}
