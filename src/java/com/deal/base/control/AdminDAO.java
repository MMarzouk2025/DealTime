package com.deal.base.control;

import java.sql.Connection;

public class AdminDAO {
    Connection mConn;

    public AdminDAO(Connection c) {
        mConn = c;
    }
    
}
