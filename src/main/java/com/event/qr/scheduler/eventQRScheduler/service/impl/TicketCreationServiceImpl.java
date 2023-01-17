package com.event.qr.scheduler.eventQRScheduler.service.impl;

import com.event.qr.scheduler.eventQRScheduler.client.IOPclient;
import com.event.qr.scheduler.eventQRScheduler.client.SmsApiClient;
import com.event.qr.scheduler.eventQRScheduler.dto.items.IOPItemsResponse;
import com.event.qr.scheduler.eventQRScheduler.dto.items.ItemData;
import com.event.qr.scheduler.eventQRScheduler.dto.orders.IOPOrdersResponse;
import com.event.qr.scheduler.eventQRScheduler.dto.orders.Order;
import com.event.qr.scheduler.eventQRScheduler.exception.DuplicateRecordException;
import com.event.qr.scheduler.eventQRScheduler.exception.IOPApiException;
import com.event.qr.scheduler.eventQRScheduler.model.QrTicket;
import com.event.qr.scheduler.eventQRScheduler.model.Seller;
import com.event.qr.scheduler.eventQRScheduler.model.TicketType;
import com.event.qr.scheduler.eventQRScheduler.repository.QrTicketRepository;
import com.event.qr.scheduler.eventQRScheduler.service.SmsService;
import com.event.qr.scheduler.eventQRScheduler.service.TicketCreationService;
import com.event.qr.scheduler.eventQRScheduler.util.AppConstatnt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public String createTicket(QrTicket qrTicket, int itemCount, String smsContent1, String smsContent2, String frontEndUrl) {
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


            logger.info("__________URL send for cus :"+frontEndUrl);

            String smsContent = smsContent1 +" "+frontEndUrl+" "+smsContent2;
            //logger.info("SMS content "+smsContent);
            if (qrTicket.getMobileNo() != null && !qrTicket.getMobileNo().equals("")) {

                logger.info("______sending sms to mobileNo :"+qrTicket.getMobileNo());
                try {
                    smsService.sendSMS(qrTicket.getMobileNo(), smsContent, ticketId); //TODO - uncomment this before go live
                    //update sms send status
                    qrTicketRepository.updateTicketStatus(qrTicket.getOrderNo(),"S");
                } catch (Exception e) {
                    logger.info("__________send sms Exception : "+e.getMessage());
                }

            } else {

                logger.info("_____sending email for.....NOT implemented yet");
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
    public void ticketProcess() {

        try {
            //get details from config table
            List<Seller> sellerList = qrTicketRepository.getSellerConfigDetails();
            logger.info("_____seller list :"+sellerList);

            if (!sellerList.isEmpty()) {
                //iterate through list and call API method
                for (Seller seller : sellerList) {
                    logger.info("_____seller :"+seller.getSellerType());
                    getTicketDetailsFromAPI(seller.getAppKey(),seller.getAppSecret(),seller.getAccessToken(),seller.getSmsContent1(),
                            seller.getSmsContent2(),seller.getFrontEndUrl(),seller.getSellerType());
                }
            } else {
                logger.info("_____seller config list is empty...");
            }


        } catch (Exception e) {
            logger.info("_____Exception : "+e.getMessage());
        }

    }

    @Override
    public void getTicketDetailsFromAPI(String appKey, String appSecret, String accessToken, String smsContent1,
                                        String smsContent2, String frontEndUrl, String sellerType) {

        logger.info("__________get ticket details from API....");

        IOPclient ioPclient = new IOPclient();
        SmsApiClient smsApiClient = new SmsApiClient();
        List<Order> orderList = new ArrayList<>();
        String smsAutToken;
        try {

            //logger.info("__________getting SMS Auth token.....");
            //smsAutToken = smsApiClient.smsApi2AuthToken();
            //smsAutToken = "1671013606667"; /** Temporary hard coded access tocken*/

            IOPOrdersResponse iopOrdersResponse = ioPclient.getOrders(appKey,appSecret,accessToken);
            orderList = iopOrdersResponse.getData().getOrders();
            Pattern p = Pattern.compile("^\\d{10}$");
            logger.info("_____order list : "+orderList.toString());

            //iterate through the order
            for (Order order : orderList) {

                int itemCount = 0;

                QrTicket qrTicket = new QrTicket();
                List<ItemData> itemDataList = new ArrayList<>();
                List<TicketType> ticketTypeList = new ArrayList<>();
                logger.info("_____order No : " + order.getOrder_number());
                qrTicket.setOrderNo(order.getOrder_number());

                //consume orders items api and get items list and create list
                IOPItemsResponse iopItemsResponse = ioPclient.getItems(order.getOrder_number(),appKey,appSecret,accessToken);
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

                for (ItemData itemData : itemDataList) {

                    TicketType ticketType = new TicketType();
                    logger.info("_____order id from items : " + itemData.getOrder_item_id());
                    ticketType.setOrderNo(order.getOrder_number());
                    logger.info("___________Variation : " + itemData.getVariation());
                    ticketType.setTicketType(itemData.getName()); //set ticket type
                    ticketType.setSku(itemData.getSku()); //set sku
                    ticketType.setVaration(itemData.getVariation()); //set variation
                    ticketTypeList.add(ticketType);

                    orderItemIdList.add(String.valueOf(itemData.getOrder_item_id()));
                }

                //add ticket type list to qr ticket object
                qrTicket.setTicketTypeList(ticketTypeList);
                logger.info("_____qrTicket Obj :"+qrTicket.toString());

                //call create ticket method
                try {

                    //set seller
                    qrTicket.setSeller(sellerType);

                    createTicket(qrTicket,itemCount,smsContent1,smsContent2,frontEndUrl); //create qr ticket and send sms


                } catch (Exception e) {

                    logger.info("__________error from create ticket method");

                }
                    //logger.info("_____sleep one sec....");
                    //Thread.sleep(1000); //wait one second - sms gateway problem


                //consume update order status API
                logger.info("_______order item id list :"+orderItemIdList.toString());
                ioPclient.updateTicketStatus(orderItemIdList,appKey,appSecret,accessToken); //TODO - uncomment before go live


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
//        final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
//        final byte[] hashbytes = digest.digest(
//                qrStr.getBytes(StandardCharsets.UTF_8));
//        String sha3Hex = bytesToHex(hashbytes);

        return qrStr;

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
