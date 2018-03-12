package com.deal.base.control;

/* Marzouk */
import com.deal.base.model.Customer;
import com.deal.base.model.Order;
import com.deal.base.model.Product;
import com.deal.control.DbHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * ********
 */
/* Nagib */
/**
 * ********
 */
/* Mokhtar */
/**
 * ********
 */
/* Ibrahim */
/**
 * ********
 */
/* Yomna */
/**
 * ********
 */
public class OrderDAO {

    /* Marzouk */
    private Connection mConn;
    public static final String SUCCESSFUL_INSERT = "order has been created successfully";
    public static final String SUCCESSFUL_UPDATE = "order has been updated successfully";
    public static final String SUCCESSFUL_DELETE = "order has been deleted successfully";
    public static final String EXCEPTION = "exception happened";

    public OrderDAO(Connection mConn) {
        this.mConn = mConn;
    }

    public Order retrieveOrder(long orderId) {
        Order order = null;
        try {
            ResultSet results = mConn.createStatement().executeQuery("SELECT CUSTOMER_ID, PRODUCT_ID, QUANTITY, STATUS\n"
                    + "FROM DEALTIME.ORDERS\n"
                    + "WHERE ORDER_ID = " + orderId);
            while (results.next()) {
                order = new Order();
                order.setOrderId(orderId);
                Customer orderCustomer = DbHandler.getCustomerDAO().retrieveCustomer(results.getLong("CUSTOMER_ID"));
                order.setCustomer(orderCustomer);
                Product orderProduct = DbHandler.getProductDAO().retrieveProduct(results.getLong("PRODUCT_ID"));
                order.setOrderProduct(orderProduct);
                order.setQuantity(results.getInt("QUANTITY"));
                order.setStatus(results.getString("STATUS").charAt(0));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                mConn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return order;
    }

    public ArrayList<Order> retrieveCustomerOrders(Customer customer) {
        ArrayList<Order> customerOrders = new ArrayList();
        try {
            ResultSet results = mConn.createStatement().executeQuery("SELECT ORDER_ID, PRODUCT_ID, QUANTITY, STATUS\n"
                    + "FROM DEALTIME.ORDERS\n"
                    + "WHERE CUSTOMER_ID = " + customer.getCustId() + "and QUANTITY > 0 and STATUS ='c'");
            while (results.next()) {
                Order order = new Order();
                order.setOrderId(results.getLong("ORDER_ID"));
                order.setCustomer(customer);
                Product orderProduct = DbHandler.getProductDAO().retrieveProduct(results.getLong("PRODUCT_ID"));
                order.setOrderProduct(orderProduct);
                order.setQuantity(results.getInt("QUANTITY"));
                order.setStatus(results.getString("STATUS").charAt(0));
                customerOrders.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                mConn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return customerOrders;
    }

    public String insertOrder(Order order) {
        String result;
        try {
            PreparedStatement stmt = mConn.prepareStatement("INSERT INTO DEALTIME.ORDERS (\n"
                    + "ORDER_ID, CUSTOMER_ID, PRODUCT_ID, \n"
                    + "QUANTITY, STATUS) \n"
                    + "VALUES (ORDERS_SEQ.NEXTVAL, ?, ?, ?, 'c')");
            if (order.getCustomer() == null) {
                stmt.setNull(1, Types.BIGINT);
            } else {
                stmt.setLong(1, order.getCustomer().getCustId());
            }
            if (order.getOrderProduct() == null) {
                stmt.setNull(2, Types.BIGINT);
            } else {
                stmt.setLong(2, order.getOrderProduct().getProductId());
            }
            stmt.setInt(3, order.getQuantity());
//            stmt.setc(4, order.getStatus());
            stmt.execute();
            result = SUCCESSFUL_INSERT;
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = EXCEPTION;
        } finally {
            try {
                mConn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public String updateOrder(Order order) {
        String result;
        try {
            PreparedStatement stmt = mConn.prepareStatement("UPDATE DEALTIME.ORDERS\n"
                    + "SET CUSTOMER_ID = ?, PRODUCT_ID = ?, QUANTITY = ?, STATUS = ?\n"
                    + "WHERE  ORDER_ID = " + order.getOrderId());
            if (order.getCustomer() == null) {
                stmt.setNull(2, Types.BIGINT);
            } else {
                stmt.setLong(2, order.getCustomer().getCustId());
            }
            if (order.getOrderProduct() == null) {
                stmt.setNull(3, Types.BIGINT);
            } else {
                stmt.setLong(3, order.getOrderProduct().getProductId());
            }
            stmt.setInt(3, order.getQuantity());
            stmt.setString(4, String.valueOf(order.getStatus()));
            stmt.execute();
            result = SUCCESSFUL_UPDATE;
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = EXCEPTION;
        } finally {
            try {
                mConn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /*
    public void invalidateProductOrders(long productId) {
        try {
            PreparedStatement stmt = mConn.prepareStatement("UPDATE DEALTIME.ORDERS\n"
                    + "SET PRODUCT_ID = NULL\n"
                    + "WHERE  PRODUCT_ID = " + productId);
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                mConn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
     */
    public String deleteOrder(Order order) {
        String result;
        try {
            PreparedStatement stmt = mConn.prepareStatement("DELETE DEALTIME.ORDERS WHERE ORDER_ID = " + order.getOrderId());
            stmt.execute();
            result = SUCCESSFUL_DELETE;
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = EXCEPTION;
        } finally {
            try {
                mConn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public String deleteCustomerOrders(Customer customer) {
        String result;
        try {
            PreparedStatement stmt = mConn.prepareStatement("DELETE DEALTIME.ORDERS WHERE CUSTOMER_ID = " + customer.getCustId());
            stmt.execute();
            result = SUCCESSFUL_DELETE;
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = EXCEPTION;
        } finally {
            try {
                mConn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * ********
     */
    /* Nagib */
//    
    public String updateOrder(int orderID, int qnt) {
        String result;
        try {
            PreparedStatement stmt = mConn.prepareStatement("UPDATE DEALTIME.ORDERS\n"
                    + "SET  QUANTITY = " + qnt + " WHERE  ORDER_ID = " + orderID);

            stmt.execute();
            result = SUCCESSFUL_UPDATE;
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = EXCEPTION;
        } finally {
            try {
                mConn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList<Order> getOrderByProduct(Customer customer, int productId) {
        ArrayList<Order> customerOrders = new ArrayList();
        try {
            ResultSet results = mConn.createStatement().executeQuery("SELECT ORDER_ID, PRODUCT_ID, QUANTITY, STATUS\n"
                    + "FROM DEALTIME.ORDERS\n"
                    + "WHERE CUSTOMER_ID = " + customer.getCustId() + "and  QUANTITY > 0 and PRODUCT_ID=" + productId + " and STATUS = 'c' ");
            while (results.next()) {
                Order order = new Order();
                order.setOrderId(results.getLong("ORDER_ID"));
                order.setCustomer(customer);
                Product orderProduct = DbHandler.getProductDAO().retrieveProduct(results.getLong("PRODUCT_ID"));
                order.setOrderProduct(orderProduct);
                order.setQuantity(results.getInt("QUANTITY"));
                order.setStatus(results.getString("STATUS").charAt(0));
                customerOrders.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                mConn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return customerOrders;
    }
    
     public String updateCustomerOrders(int id) {
        String result;
        try {
            PreparedStatement stmt = mConn.prepareStatement("UPDATE DEALTIME.ORDERS\n"
                    + " set STATUS = 'd' \n"
                    + " WHERE  CUSTOMER_ID = " + id);
            
            System.out.println("the id is "+id);
            stmt.execute();
            result = SUCCESSFUL_UPDATE;
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = EXCEPTION;
        } finally {
            try {
                mConn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


//     
    /* Mokhtar */
    /**
     * ********
     */
    /* Ibrahim */
    /**
     * ********
     */
    /* Yomna */
    /**
     * ********
     */
}
