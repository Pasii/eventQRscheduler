package com.event.qr.scheduler.eventQRScheduler.model;

public class TicketType {

    private String ticketType;
    private int ticketCount;
    private String orderNo;

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

    @Override
    public String toString() {
        return "TicketType{" +
                "ticketType='" + ticketType + '\'' +
                ", ticketCount=" + ticketCount +
                ", orderNo='" + orderNo + '\'' +
                '}';
    }
}
