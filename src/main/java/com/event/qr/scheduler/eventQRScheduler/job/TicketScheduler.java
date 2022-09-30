package com.event.qr.scheduler.eventQRScheduler.job;

import com.event.qr.scheduler.eventQRScheduler.exception.InvalidFormatException;
import com.event.qr.scheduler.eventQRScheduler.exception.SendSmsException;
import com.event.qr.scheduler.eventQRScheduler.model.QrTicket;
import com.event.qr.scheduler.eventQRScheduler.service.SmsService;
import com.event.qr.scheduler.eventQRScheduler.service.TicketCreationService;
import com.event.qr.scheduler.eventQRScheduler.util.AppConstatnt;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

@Configuration
@EnableScheduling
public class TicketScheduler {

    @Autowired
    TicketCreationService ticketCreationService;

    @Autowired
    SmsService smsService;

    @Scheduled(fixedDelay = 300000)
    public void createTicketJob() {
        System.out.println("_____Ticket creating task running.....");

        ticketCreationService.getTicketDetailsFromAPI();

//        try {
//            smsService.sendSMS("0717855481", AppConstatnt.SMS_CONTENT+" "+AppConstatnt.FRONTEND_BASE_URL+"qr-loader/30000001","1234");
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        } catch (InvalidFormatException e) {
//            throw new RuntimeException(e);
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        } catch (SendSmsException e) {
//            throw new RuntimeException(e);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }

    }
}
