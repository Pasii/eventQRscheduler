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
public class Data {
    public int count;
    public ArrayList<Order> orders;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Data{" +
                "count=" + count +
                ", orders=" + orders +
                '}';
    }
}
