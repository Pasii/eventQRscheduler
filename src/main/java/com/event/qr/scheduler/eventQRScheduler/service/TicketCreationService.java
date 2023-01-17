package com.event.qr.scheduler.eventQRScheduler.service;

import com.event.qr.scheduler.eventQRScheduler.model.QrTicket;

public interface TicketCreationService {

    String createTicket(QrTicket qrTicket, int itemCount, String smsContent1, String smsContent2, String frontEndUrl);

    void getTicketDetailsFromAPI(String appKey, String appSecret, String accessToken, String smsContent1, String smsContent2, String frontEndUrl, String sellerType);

    void ticketProcess();
}

