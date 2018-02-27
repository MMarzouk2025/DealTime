package com.deal.base.model;

import java.io.Serializable;

public class Order implements Serializable {

    private long orderId;
    private Product orderProduct;

    public Order() {
    }

    public Order(Product orderProduct) {
        this.orderProduct = orderProduct;
    }

    public long getOrderId() {
        return orderId;
    }

    public Product getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(Product orderProduct) {
        this.orderProduct = orderProduct;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", orderProduct=" + orderProduct + '}';
    }
    
}
