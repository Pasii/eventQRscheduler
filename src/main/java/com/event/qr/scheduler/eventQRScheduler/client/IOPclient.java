package com.event.qr.scheduler.eventQRScheduler.client;

import com.event.qr.scheduler.eventQRScheduler.dto.items.IOPItemsResponse;
import com.event.qr.scheduler.eventQRScheduler.dto.items.IOPRtsResponse;
import com.event.qr.scheduler.eventQRScheduler.dto.orders.IOPOrdersResponse;
import com.event.qr.scheduler.eventQRScheduler.exception.IOPApiException;
import com.event.qr.scheduler.eventQRScheduler.util.AppConstatnt;
import com.global.iop.api.IopClientImpl;
import com.global.iop.api.IopRequest;
import com.global.iop.api.IopResponse;
import com.global.iop.util.ApiException;
import com.google.gson.Gson;

import java.util.List;

public class IOPclient {

//    String accessToken = "50000100e03j6ow152f92d8or9Z5jzEotAwuE7DdsgsrTFfjwmVExu9kK0pTT";
//
//    //"country":"LK",
//    String refresh_token = "500011016030cud1cba7badjr9svgcDhtepxVDHgMJ3dQV9iZEOPhLo3Ddnao";

    IopClientImpl client = new IopClientImpl("https://api.daraz.lk/rest",
            "500678", "yXCsSFrDM52DtwmTOfKRnsUzibsztMes") ;

    public IOPOrdersResponse getOrders() throws ApiException, IOPApiException {
        //IopClientImpl client = new IopClientImpl("https://api.daraz.lk/rest", "500678", "yXCsSFrDM52DtwmTOfKRnsUzibsztMes");
        IopRequest request = new IopRequest();
        request.setApiName("/orders/get");
        request.setHttpMethod("GET");
        //request.addApiParameter("update_before", "2022-09-22T122:00:00+08:00");
        request.addApiParameter("sort_direction", "DESC");
        request.addApiParameter("offset", "0");
        request.addApiParameter("limt", "100");
        //request.addApiParameter("update_after", "2018-02-10T09:00:00+08:00");
        request.addApiParameter("sort_by", "updated_at");
        //request.addApiParameter("created_before", "2022-09-23T00:10:00+08:00");
        request.addApiParameter("created_after", "2017-02-10T09:00:00+08:00");
        request.addApiParameter("status", "pending");
        IopResponse response = client.execute(request, AppConstatnt.ACCESS_TOKEN);
        //System.out.println(response.getBody());

        IOPOrdersResponse iopResponse = new Gson().fromJson(response.getBody(), IOPOrdersResponse.class);

        System.out.println("code :"+iopResponse.getCode()+" request id :"+iopResponse.request_id);

        if (!iopResponse.getCode().equals("0")) {
            throw new IOPApiException("IOP Api error");
        }

        return iopResponse;
    }


    public void getOrder(String order_number) throws ApiException {

        IopRequest request = new IopRequest();
        request.setApiName("/order/get");
        request.addApiParameter("order_id", order_number);
        IopResponse response = client.execute(request, AppConstatnt.ACCESS_TOKEN);
        System.out.println(response.getBody());
    }

    public IOPItemsResponse getItems(String orderNo) throws ApiException {

        IopRequest request = new IopRequest();
        request.setApiName("/order/items/get");
        request.setHttpMethod("GET");
        request.addApiParameter("order_id", orderNo);
        IopResponse response = client.execute(request, AppConstatnt.ACCESS_TOKEN);
        //System.out.println(response.getBody());
        IOPItemsResponse iopItemsResponse = new Gson().fromJson(response.getBody(),IOPItemsResponse.class);
        System.out.println(iopItemsResponse.toString());
        return iopItemsResponse;
    }

    public void updateTicketStatus(List<String> orderIdList) throws ApiException, IOPApiException {

        IopRequest request = new IopRequest();
        request.setApiName("/order/rts");
        request.addApiParameter("order_item_ids", orderIdList.toString());
        request.addApiParameter("delivery_type", "dropship");
        IopResponse response = client.execute(request, AppConstatnt.ACCESS_TOKEN);
        IOPRtsResponse iopRtsResponse = new Gson().fromJson(response.getBody(),IOPRtsResponse.class);
        //System.out.println(response.getBody());
        System.out.println(iopRtsResponse.toString());

        if (!iopRtsResponse.getCode().equals("0")) {
            throw new IOPApiException("IOP Api error");
        }
    }
}
