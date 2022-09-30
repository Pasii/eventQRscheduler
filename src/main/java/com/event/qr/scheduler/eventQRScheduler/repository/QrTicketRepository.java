package com.event.qr.scheduler.eventQRScheduler.repository;

import com.event.qr.scheduler.eventQRScheduler.exception.DuplicateRecordException;
import com.event.qr.scheduler.eventQRScheduler.model.QrTicket;

import java.sql.SQLException;

public interface QrTicketRepository {

    void addTicketDetails(QrTicket qrTicket) throws SQLException, DuplicateRecordException;

    public void updateTicketStatus(String orderNo, String status);
}
