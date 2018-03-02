package com.deal.base.control;

import java.sql.Connection;

public class CategoryDAO {
    Connection mConn;

    public CategoryDAO(Connection mConn) {
        this.mConn = mConn;
    }
    
}
