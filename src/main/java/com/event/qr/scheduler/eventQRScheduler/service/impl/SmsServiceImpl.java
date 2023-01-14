package com.event.qr.scheduler.eventQRScheduler.service.impl;

import com.event.qr.scheduler.eventQRScheduler.client.SmsApiClient;
import com.event.qr.scheduler.eventQRScheduler.dto.SmsApi2Request;
import com.event.qr.scheduler.eventQRScheduler.exception.InvalidFormatException;
import com.event.qr.scheduler.eventQRScheduler.exception.SendSmsException;
import com.event.qr.scheduler.eventQRScheduler.service.SmsService;
import com.event.qr.scheduler.eventQRScheduler.util.AppConstatnt;
import com.event.qr.scheduler.eventQRScheduler.util.CommonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

@Service
public class SmsServiceImpl implements SmsService {

    Logger logger = LogManager.getLogger(SmsServiceImpl.class);

    public void sendSMS(String mobileNo, String smsContent, String refNo) throws NoSuchAlgorithmException, InvalidFormatException, URISyntaxException, SendSmsException, JsonProcessingException {

        logger.info("__________Send SMS to : "+mobileNo);


        if (mobileNo.length() < 10) {
            throw new InvalidFormatException("Invalid mobile no");
        }

        String formattedMobileNo = "94"+mobileNo.substring(mobileNo.length() - 9);
        logger.info("______ 94 formatted mobile no :"+formattedMobileNo);



//        SmsSendRequest request = new SmsSendRequest();
//        MessageElement messageElement = new MessageElement();
//        List<MessageElement> messageElementList = new ArrayList<>();
//
//        messageElement.setClientRef(refNo);
//        messageElement.setNumber(formattedMobileNo);
//        messageElement.setMask("TEST");
//        messageElement.setText(smsContent);
//        messageElement.setCampaignName("Darazian campaign");
//
//        messageElementList.add(messageElement);
//
//        request.setMessages(messageElementList);

        //logger.info(request.toString());

        SmsApi2Request request = new SmsApi2Request();
        request.setMsisdn(formattedMobileNo); //set mobile no
        request.setChannel("9"); //Short sms (TRANSACTIONAL up to 160 characters
        request.setMsg(smsContent);
        request.setMt_port(AppConstatnt.SMS_MASK);

        //set current timestamp as start time
        request.setS_time(CommonUtil.getFormattedTimeString());

        //set current timestamp + 1 days as end time
        request.setE_time(CommonUtil.addDaysToCurrentTime(1));

        SmsApiClient smsApiClient = new SmsApiClient();
        smsApiClient.sendSMSApi2(request);

    }

}
