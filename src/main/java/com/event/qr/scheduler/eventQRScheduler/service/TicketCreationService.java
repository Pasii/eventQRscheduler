package com.event.qr.scheduler.eventQRScheduler.service;

import com.event.qr.scheduler.eventQRScheduler.model.QrTicket;

public interface TicketCreationService {

    String createTicket(QrTicket qrTicket, int itemCount);

    void getTicketDetailsFromAPI();
}

