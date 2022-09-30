package com.event.qr.scheduler.eventQRScheduler.dto.items;

import java.util.ArrayList;

public class IOPItemsResponse {

    public ArrayList<ItemData> data;
    public String code;
    public String request_id;

    public ArrayList<ItemData> getData() {
        return data;
    }

    public void setData(ArrayList<ItemData> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    @Override
    public String toString() {
        return "IOPItemsResponse{" +
                "data=" + data +
                ", code='" + code + '\'' +
                ", request_id='" + request_id + '\'' +
                '}';
    }
}
