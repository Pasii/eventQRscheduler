package com.event.qr.scheduler.eventQRScheduler.dto.items;

public class OrderItem {

    public String order_item_id;
    public String purchase_order_id;
    public String purchase_order_number;

    public String getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(String order_item_id) {
        this.order_item_id = order_item_id;
    }

    public String getPurchase_order_id() {
        return purchase_order_id;
    }

    public void setPurchase_order_id(String purchase_order_id) {
        this.purchase_order_id = purchase_order_id;
    }

    public String getPurchase_order_number() {
        return purchase_order_number;
    }

    public void setPurchase_order_number(String purchase_order_number) {
        this.purchase_order_number = purchase_order_number;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "order_item_id='" + order_item_id + '\'' +
                ", purchase_order_id='" + purchase_order_id + '\'' +
                ", purchase_order_number='" + purchase_order_number + '\'' +
                '}';
    }
}
