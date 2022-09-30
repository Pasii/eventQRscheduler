package com.event.qr.scheduler.eventQRScheduler.client;

import com.event.qr.scheduler.eventQRScheduler.dto.*;
import com.event.qr.scheduler.eventQRScheduler.exception.SendSmsException;
import com.event.qr.scheduler.eventQRScheduler.util.AppConstatnt;
import com.event.qr.scheduler.eventQRScheduler.util.CommonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;


public class SmsApiClient {

    @Autowired
    RestTemplate restTemplate;

    Logger logger = LogManager.getLogger(SmsApiClient.class);

    public void sendSMS(SmsSendRequest request) throws URISyntaxException, NoSuchAlgorithmException, SendSmsException, JsonProcessingException {

        String pwd = AppConstatnt.SMS_API_PWD;
        String digestStr = CommonUtil.getMd5HashedString(pwd);

        String url = "https://richcommunication.dialog.lk/api/sms/send";

        String formattedTime = CommonUtil.getIsoFormattedTimeString();
        logger.info("Date format "+formattedTime);

        HttpHeaders headers = new HttpHeaders();

        headers.set("USER", AppConstatnt.SMS_API_USER);
        headers.set("DIGEST", digestStr);
        headers.set("CREATED",formattedTime);
        headers.set("Content-Type", "application/json");

        HttpEntity<SmsSendRequest> requestEntity = new HttpEntity<>(request, headers);

        restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.POST, requestEntity, String.class);

        String jsonInput= response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info(jsonInput);
        SmsSendResponse resObj = objectMapper.readValue(jsonInput, new TypeReference<SmsSendResponse>(){});

        logger.info("&&&&&&&&&&&&&&&&&&&&&&&&&&&response "+resObj.toString());
        if (resObj.getMessages().get(0).getResultCode() != 0) {
            throw new SendSmsException("Sending sms failed");
        }
        logger.info("______________sent sms.....");

    }

    public void sendSMSApi2(SmsApi2Request request) throws SendSmsException {

        String url = AppConstatnt.SMS_API_2_URL;

        String authToken = smsApi2AuthToken();

        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization",authToken);
        headers.set("Content-Type", "application/json");
        headers.set("Accept", "application/json");

        HttpEntity<SmsApi2Request> requestEntity = new HttpEntity<>(request, headers);

        restTemplate = new RestTemplate();

        ResponseEntity<SmsApi2Response> response = restTemplate.exchange(url,HttpMethod.POST, requestEntity, SmsApi2Response.class);

        logger.info("_____sms api 2 response "+response.toString());
        if (!response.getBody().getError().equals("0")) {
            throw new SendSmsException("Sms exception failed");
        }

    }

    public String smsApi2AuthToken() throws SendSmsException {

        String api2UserName = AppConstatnt.SMS_API_2_USER;
        String api2Password = AppConstatnt.SMS_API_2_PWD;

        String api2AuthUrl = AppConstatnt.SMS_API_2_AUTH_URL;

        SmsApi2AuthRequest request = new SmsApi2AuthRequest();
        request.setU_name(api2UserName);
        request.setPasswd(api2Password);

        HttpHeaders headers = new HttpHeaders();

        headers.set("Content-Type", "application/json");

        HttpEntity<SmsApi2AuthRequest> requestEntity = new HttpEntity<>(request, headers);

        restTemplate = new RestTemplate();

        ResponseEntity<SmsApi2AuthResponse> response = restTemplate.exchange(api2AuthUrl,HttpMethod.POST, requestEntity, SmsApi2AuthResponse.class);

        logger.info("__________auth response "+response.toString());

        if (response.getBody().getError() != null) {
            throw new SendSmsException("Authentication failed");
        }

        return response.getBody().getAccess_token();


    }


}
