package com.event.qr.scheduler.eventQRScheduler.service.impl;

import com.event.qr.scheduler.eventQRScheduler.client.IOPclient;
import com.event.qr.scheduler.eventQRScheduler.dto.items.IOPItemsResponse;
import com.event.qr.scheduler.eventQRScheduler.dto.items.ItemData;
import com.event.qr.scheduler.eventQRScheduler.dto.orders.IOPOrdersResponse;
import com.event.qr.scheduler.eventQRScheduler.dto.orders.Order;
import com.event.qr.scheduler.eventQRScheduler.exception.DuplicateRecordException;
import com.event.qr.scheduler.eventQRScheduler.exception.IOPApiException;
import com.event.qr.scheduler.eventQRScheduler.model.QrTicket;
import com.event.qr.scheduler.eventQRScheduler.model.TicketType;
import com.event.qr.scheduler.eventQRScheduler.repository.QrTicketRepository;
import com.event.qr.scheduler.eventQRScheduler.service.SmsService;
import com.event.qr.scheduler.eventQRScheduler.service.TicketCreationService;
import com.event.qr.scheduler.eventQRScheduler.util.AppConstatnt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TicketCreationServiceImpl implements TicketCreationService {

    Logger logger = LogManager.getLogger(TicketCreationServiceImpl.class);

    @Autowired
    QrTicketRepository qrTicketRepository;

    @Autowired
    SmsService smsService;

    @Override
    public String createTicket(QrTicket qrTicket) {
        String qrString = null;
        String ticketId = null;

        try {

            //validate request parameters

            //generate id from ticket details
            ticketId = generateTicketId(qrTicket.getOrderNo());
            qrTicket.setTicketId(ticketId);

            //generate qr string from ticket details
            qrString = getHashedQrString(qrTicket.getMobileNo(),qrTicket.getOrderNo());
            logger.info("__________"+qrString+"_______"+ qrString.length());
            qrTicket.setQrString(qrString);

            logger.info("__________qr ticket obj "+qrTicket.toString());

            qrTicketRepository.addTicketDetails(qrTicket);

            //qrTicketRepository.addTicketTypes

            //http://localhost:3000/qr-loader/40000001
            String urlForSendToCus = AppConstatnt.FRONTEND_BASE_URL+"qr-loader/"+ticketId;
            logger.info("__________URL send for cus :"+urlForSendToCus);

            String smsContent = AppConstatnt.SMS_CONTENT+" "+urlForSendToCus;

            if (qrTicket.getMobileNo() != null && !qrTicket.getMobileNo().equals("")) {

                logger.info("______sending sms to mobileNo :"+qrTicket.getMobileNo());
                try {
                    smsService.sendSMS(qrTicket.getMobileNo(), smsContent, ticketId);
                    //update sms send status
                    qrTicketRepository.updateTicketStatus(qrTicket.getOrderNo(),"S");
                } catch (Exception e) {
                    logger.info("__________send sms Exception : "+e.getMessage());
                }

            } else {

                logger.info("_____sending email for.....");
                //TODO - send email - ..................
            }


        } catch (DuplicateRecordException e) {
            logger.info("__________DuplicateRecordException :"+e.getMessage());

//        } catch (InvalidFormatException e) {
//            logger.info("__________InvalidFormatException :"+e.getMessage());
//            response.setResCode(AppConstatnt.RES_CODE_1003);
//            response.setResDescription(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return qrString;
    }

    @Override
    public void getTicketDetailsFromAPI() {

        logger.info("__________get ticket details from API....");

        IOPclient ioPclient = new IOPclient();
        List<Order> orderList = new ArrayList<>();
        try {

            IOPOrdersResponse iopOrdersResponse = ioPclient.getOrders();
            orderList = iopOrdersResponse.getData().getOrders();
            Pattern p = Pattern.compile("^\\d{10}$");
            logger.info("_____order list : "+orderList.toString());

            //iterate through the order
            for (Order order : orderList) {

                QrTicket qrTicket = new QrTicket();
                List<ItemData> itemDataList = new ArrayList<>();
                List<TicketType> ticketTypeList = new ArrayList<>();
                logger.info("_____order No : "+order.getOrder_number());
                qrTicket.setOrderNo(order.getOrder_number());

                //logger.info("_____mobile no :"+order.getAddress_billing().getPhone());
                //qrTicket.setMobileNo(order.getAddress_billing().getPhone());

                //consume orders items api and get items list and create list
                IOPItemsResponse iopItemsResponse = ioPclient.getItems(order.getOrder_number());
                itemDataList = iopItemsResponse.getData();

                //check mobile no or email
                String digitalDeliverInfo = itemDataList.get(0).getDigital_delivery_info();
                Matcher m = p.matcher(digitalDeliverInfo);
                if (m.matches()) {
                    qrTicket.setMobileNo(digitalDeliverInfo);
                } else {
                    qrTicket.setEmail(digitalDeliverInfo);
                }

                List<String> orderItemIdList = new ArrayList<>();

                // iterate through the item list
                for (ItemData itemData : itemDataList) {

                    TicketType ticketType = new TicketType();
                    logger.info("_____order id from items : "+itemData.getOrder_item_id());
                    ticketType.setOrderNo(order.getOrder_number());
                    ticketType.setTicketType(itemData.getName());
                    ticketTypeList.add(ticketType);

                    orderItemIdList.add(String.valueOf(itemData.getOrder_item_id()));
                }

                //add ticket type list to qr ticket object
                qrTicket.setTicketTypeList(ticketTypeList);
                logger.info("_____qrTicket Obj :"+qrTicket.toString());

                //call create ticket method
                try {

                    createTicket(qrTicket); //create qr ticket and send sms

                    //consume update order status API
                    logger.info("_______order item id list :"+orderItemIdList.toString());
                    ioPclient.updateTicketStatus(orderItemIdList);

                } catch (Exception e) {

                    logger.info("__________error from create ticket method");

                }

            }

        } catch (IOPApiException e) {
            logger.info("_____IOPApiException: "+e.getMessage());
        } catch (Exception e) {
            logger.info("_____Exception :"+e.getMessage());
        }

    }

    private String generateTicketId(String orderNo) {
        Random random = new Random();
        String id = String.format("%04d", random.nextInt(10000));
        return id+orderNo;
    }

    private String getHashedQrString(String mobileNo, String orderNo) throws NoSuchAlgorithmException {

        Random random = new Random();
        //String randomNumb = String.format("%04d", random.nextInt(10000));
        String currentTimestamp = String.valueOf(System.currentTimeMillis());
        String qrStr =  orderNo + currentTimestamp;
        final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        final byte[] hashbytes = digest.digest(
                qrStr.getBytes(StandardCharsets.UTF_8));
        String sha3Hex = bytesToHex(hashbytes);

        return sha3Hex;

    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
