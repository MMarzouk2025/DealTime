package com.deal.control;

import com.deal.base.control.AdminDAO;
import com.deal.base.control.CategoryDAO;
import com.deal.base.control.CustomerDAO;
import com.deal.base.control.DbConn;
import com.deal.base.control.OrderDAO;
import com.deal.base.control.ProductDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbHandler {

    private DbHandler() {
    }

    private static Connection getDbConnection() {
        Connection mConn = null;
        try {
            mConn = DbConn.getDbConn().getDataSource().getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return mConn;
    }

    public static AdminDAO getAdminDAO() {
        Connection conn = getDbConnection();
        return new AdminDAO(conn);
    }
    
    public static CustomerDAO getCustomerDAO() {
        Connection conn = getDbConnection();
        return new CustomerDAO(conn);
    }
    
    public static CategoryDAO getCategoryDAO() {
        Connection conn = getDbConnection();
        return new CategoryDAO(conn);
    }
    
    public static ProductDAO getProductDAO() {
        Connection conn = getDbConnection();
        return new ProductDAO(conn);
    }
    
    public static OrderDAO getOrderDAO() {
        Connection conn = getDbConnection();
        return new OrderDAO(conn);
    }

}
