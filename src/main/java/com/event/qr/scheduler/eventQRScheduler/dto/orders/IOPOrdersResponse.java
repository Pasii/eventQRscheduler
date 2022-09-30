
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.event.qr.scheduler.eventQRScheduler.dto.orders;

/**
 *
 * @author ajithi
 */
public class IOPOrdersResponse {

    public Data data;
    public String code;
    public String request_id;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
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
        return "IOPResponse{" +
                "data=" + data +
                ", code='" + code + '\'' +
                ", request_id='" + request_id + '\'' +
                '}';
    }
}
