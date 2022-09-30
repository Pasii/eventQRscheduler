/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.event.qr.scheduler.eventQRScheduler.dto.orders;

import java.util.ArrayList;

/**
 *
 * @author ajithi
 */
public class Order {

    public int voucher_platform;
    public double voucher;
    public String warehouse_code;
    public int voucher_seller;
    public String order_number;
    public String created_at;
    public String voucher_code;
    public boolean gift_option;
    public int shipping_fee_discount_platform;
    public String customer_last_name;
    public String promised_shipping_times;
    public String updated_at;
    public String price;
    public double shipping_fee_original;
    public String payment_method;
    public String customer_first_name;
    public int shipping_fee_discount_seller;
    public double shipping_fee;
    public String national_registration_number1;
    public int items_count;
    public String delivery_info;
    public ArrayList<String> statuses;
    public AddressBilling address_billing;
    public String extra_attributes;
    public Object order_id;
    public String gift_message;
    public String remarks;
    public AddressShipping address_shipping;

    public int getVoucher_platform() {
        return voucher_platform;
    }

    public void setVoucher_platform(int voucher_platform) {
        this.voucher_platform = voucher_platform;
    }

    public double getVoucher() {
        return voucher;
    }

    public void setVoucher(double voucher) {
        this.voucher = voucher;
    }

    public String getWarehouse_code() {
        return warehouse_code;
    }

    public void setWarehouse_code(String warehouse_code) {
        this.warehouse_code = warehouse_code;
    }

    public int getVoucher_seller() {
        return voucher_seller;
    }

    public void setVoucher_seller(int voucher_seller) {
        this.voucher_seller = voucher_seller;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getVoucher_code() {
        return voucher_code;
    }

    public void setVoucher_code(String voucher_code) {
        this.voucher_code = voucher_code;
    }

    public boolean isGift_option() {
        return gift_option;
    }

    public void setGift_option(boolean gift_option) {
        this.gift_option = gift_option;
    }

    public int getShipping_fee_discount_platform() {
        return shipping_fee_discount_platform;
    }

    public void setShipping_fee_discount_platform(int shipping_fee_discount_platform) {
        this.shipping_fee_discount_platform = shipping_fee_discount_platform;
    }

    public String getCustomer_last_name() {
        return customer_last_name;
    }

    public void setCustomer_last_name(String customer_last_name) {
        this.customer_last_name = customer_last_name;
    }

    public String getPromised_shipping_times() {
        return promised_shipping_times;
    }

    public void setPromised_shipping_times(String promised_shipping_times) {
        this.promised_shipping_times = promised_shipping_times;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public double getShipping_fee_original() {
        return shipping_fee_original;
    }

    public void setShipping_fee_original(double shipping_fee_original) {
        this.shipping_fee_original = shipping_fee_original;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getCustomer_first_name() {
        return customer_first_name;
    }

    public void setCustomer_first_name(String customer_first_name) {
        this.customer_first_name = customer_first_name;
    }

    public int getShipping_fee_discount_seller() {
        return shipping_fee_discount_seller;
    }

    public void setShipping_fee_discount_seller(int shipping_fee_discount_seller) {
        this.shipping_fee_discount_seller = shipping_fee_discount_seller;
    }

    public double getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(double shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public String getNational_registration_number1() {
        return national_registration_number1;
    }

    public void setNational_registration_number1(String national_registration_number1) {
        this.national_registration_number1 = national_registration_number1;
    }

    public int getItems_count() {
        return items_count;
    }

    public void setItems_count(int items_count) {
        this.items_count = items_count;
    }

    public String getDelivery_info() {
        return delivery_info;
    }

    public void setDelivery_info(String delivery_info) {
        this.delivery_info = delivery_info;
    }

    public ArrayList<String> getStatuses() {
        return statuses;
    }

    public void setStatuses(ArrayList<String> statuses) {
        this.statuses = statuses;
    }

    public AddressBilling getAddress_billing() {
        return address_billing;
    }

    public void setAddress_billing(AddressBilling address_billing) {
        this.address_billing = address_billing;
    }

    public String getExtra_attributes() {
        return extra_attributes;
    }

    public void setExtra_attributes(String extra_attributes) {
        this.extra_attributes = extra_attributes;
    }

    public Object getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Object order_id) {
        this.order_id = order_id;
    }

    public String getGift_message() {
        return gift_message;
    }

    public void setGift_message(String gift_message) {
        this.gift_message = gift_message;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public AddressShipping getAddress_shipping() {
        return address_shipping;
    }

    public void setAddress_shipping(AddressShipping address_shipping) {
        this.address_shipping = address_shipping;
    }

    @Override
    public String toString() {
        return "Order{" +
                "voucher_platform=" + voucher_platform +
                ", voucher=" + voucher +
                ", warehouse_code='" + warehouse_code + '\'' +
                ", voucher_seller=" + voucher_seller +
                ", order_number=" + order_number +
                ", created_at='" + created_at + '\'' +
                ", voucher_code='" + voucher_code + '\'' +
                ", gift_option=" + gift_option +
                ", shipping_fee_discount_platform=" + shipping_fee_discount_platform +
                ", customer_last_name='" + customer_last_name + '\'' +
                ", promised_shipping_times='" + promised_shipping_times + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", price='" + price + '\'' +
                ", shipping_fee_original=" + shipping_fee_original +
                ", payment_method='" + payment_method + '\'' +
                ", customer_first_name='" + customer_first_name + '\'' +
                ", shipping_fee_discount_seller=" + shipping_fee_discount_seller +
                ", shipping_fee=" + shipping_fee +
                ", national_registration_number1='" + national_registration_number1 + '\'' +
                ", items_count=" + items_count +
                ", delivery_info='" + delivery_info + '\'' +
                ", statuses=" + statuses +
                ", address_billing=" + address_billing +
                ", extra_attributes='" + extra_attributes + '\'' +
                ", order_id=" + order_id +
                ", gift_message='" + gift_message + '\'' +
                ", remarks='" + remarks + '\'' +
                ", address_shipping=" + address_shipping +
                '}';
    }
}
