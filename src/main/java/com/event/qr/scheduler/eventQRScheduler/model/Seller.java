package com.event.qr.scheduler.eventQRScheduler.model;

public class Seller {

    private Long id;
    private String sellerType;
    private String sellerStatus;
    private String appKey;
    private String appSecret;
    private String accessToken;
    private String smsContent1;
    private String smsContent2;
    private String frontEndUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellerType() {
        return sellerType;
    }

    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }

    public String getSellerStatus() {
        return sellerStatus;
    }

    public void setSellerStatus(String sellerStatus) {
        this.sellerStatus = sellerStatus;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getSmsContent1() {
        return smsContent1;
    }

    public void setSmsContent1(String smsContent1) {
        this.smsContent1 = smsContent1;
    }

    public String getSmsContent2() {
        return smsContent2;
    }

    public void setSmsContent2(String smsContent2) {
        this.smsContent2 = smsContent2;
    }

    public String getFrontEndUrl() {
        return frontEndUrl;
    }

    public void setFrontEndUrl(String frontEndUrl) {
        this.frontEndUrl = frontEndUrl;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", sellerType='" + sellerType + '\'' +
                ", sellerStatus='" + sellerStatus + '\'' +
                ", appKey='" + appKey + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", smsContent1='" + smsContent1 + '\'' +
                ", smsContent2='" + smsContent2 + '\'' +
                ", frontEndUrl='" + frontEndUrl + '\'' +
                '}';
    }
}
