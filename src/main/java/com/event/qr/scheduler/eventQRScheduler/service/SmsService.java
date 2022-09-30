package com.event.qr.scheduler.eventQRScheduler.service;

import com.event.qr.scheduler.eventQRScheduler.exception.InvalidFormatException;
import com.event.qr.scheduler.eventQRScheduler.exception.SendSmsException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

public interface SmsService {

    public void sendSMS(String mobileNo, String smsContent, String refNo) throws NoSuchAlgorithmException, InvalidFormatException, URISyntaxException, SendSmsException, JsonProcessingException;
}
