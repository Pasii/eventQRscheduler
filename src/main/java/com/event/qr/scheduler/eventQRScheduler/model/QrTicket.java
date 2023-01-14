package com.event.qr.scheduler.eventQRScheduler.model;

import java.util.List;

public class QrTicket {

    private int id;
    private String ticketId;
    private String orderNo;
    private String mobileNo;
    private String qrString;
    private int noOfTickets;
    private String ticketStatus;
    private List<TicketType> ticketTypeList;
    private String email;
    private String seller;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getQrString() {
        return qrString;
    }

    public void setQrString(String qrString) {
        this.qrString = qrString;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public void setNoOfTickets(int noOfTickets) {
        this.noOfTickets = noOfTickets;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public List<TicketType> getTicketTypeList() {
        return ticketTypeList;
    }

    public void setTicketTypeList(List<TicketType> ticketTypeList) {
        this.ticketTypeList = ticketTypeList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "QrTicket{" +
                "id=" + id +
                ", ticketId='" + ticketId + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", qrString='" + qrString + '\'' +
                ", noOfTickets=" + noOfTickets +
                ", ticketStatus='" + ticketStatus + '\'' +
                ", ticketTypeList=" + ticketTypeList +
                ", email='" + email + '\'' +
                ", seller='" + seller + '\'' +
                '}';
    }
}
