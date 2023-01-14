package com.event.qr.scheduler.eventQRScheduler.model;

public class TicketType {

    private String ticketType;
    private int ticketCount;
    private String orderNo;
    private String sku;
    private String varation;

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getVaration() {
        return varation;
    }

    public void setVaration(String varation) {
        this.varation = varation;
    }

    @Override
    public String toString() {
        return "TicketType{" +
                "ticketType='" + ticketType + '\'' +
                ", ticketCount=" + ticketCount +
                ", orderNo='" + orderNo + '\'' +
                ", sku='" + sku + '\'' +
                ", varation='" + varation + '\'' +
                '}';
    }
}
