package com.deal.base.model;

public class Order {

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

}
