package com.deal.base.control;

import java.sql.Connection;

public class OrderDAO {
    Connection mConn;

    public OrderDAO(Connection mConn) {
        this.mConn = mConn;
    }
    
}
