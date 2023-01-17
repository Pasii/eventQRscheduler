package com.event.qr.scheduler.eventQRScheduler.repository;

import com.event.qr.scheduler.eventQRScheduler.exception.DuplicateRecordException;
import com.event.qr.scheduler.eventQRScheduler.model.QrTicket;
import com.event.qr.scheduler.eventQRScheduler.model.Seller;

import java.sql.SQLException;
import java.util.List;

public interface QrTicketRepository {

    void addTicketDetails(QrTicket qrTicket) throws SQLException, DuplicateRecordException;

    public void updateTicketStatus(String orderNo, String status);

    List<Seller> getSellerConfigDetails();
}
