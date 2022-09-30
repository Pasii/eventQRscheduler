package com.event.qr.scheduler.eventQRScheduler.dto.items;

import java.util.List;

public class ItemRtsData {

    List<OrderItem> order_items;

    public List<OrderItem> getOrder_items() {
        return order_items;
    }

    public void setOrder_items(List<OrderItem> order_items) {
        this.order_items = order_items;
    }

    @Override
    public String toString() {
        return "ItemRtsData{" +
                "order_items=" + order_items +
                '}';
    }
}
