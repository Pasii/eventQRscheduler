package com.event.qr.scheduler.eventQRScheduler.dto.items;

public class IOPRtsResponse {

    public String code;
    public ItemRtsData data;
    public String request_id;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ItemRtsData getData() {
        return data;
    }

    public void setData(ItemRtsData data) {
        this.data = data;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    @Override
    public String toString() {
        return "IOPRtsResponse{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", request_id='" + request_id + '\'' +
                '}';
    }
}
