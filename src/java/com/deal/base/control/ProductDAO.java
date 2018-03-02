package com.deal.base.control;

import java.sql.Connection;

public class ProductDAO {
    Connection mConn;

    public ProductDAO(Connection mConn) {
        this.mConn = mConn;
    }
    
}
