package com.deal.base.model;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable {

    private long cartId;
    private List<Order> orders;

    public Cart() {
    }

    public Cart(List<Order> orders) {
        this.orders = orders;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Cart{" + "cartId=" + cartId + ", orders=" + orders + '}';
    }

}
