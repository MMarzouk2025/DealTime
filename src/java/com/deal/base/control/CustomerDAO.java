package com.deal.base.control;

/* Marzouk */
import com.deal.base.model.Customer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
public class CustomerDAO {

    /* Marzouk */
    private Connection mConn;
    public static final String SUCCESSFUL_INSERT = "registeration has been done successfully";
    public static final String SUCCESSFUL_UPDATE = "user info has been updated successfully";
    public static final String SUCCESSFUL_DELETE = "user has been deleted successfully";
    public static final String EXISTING_EMAIL = "This email already exists";
    public static final String DELETING_CUSTOMER_ERROR = "error while deleting customer";
    public static final String EXCEPTION = "exception happened";

    public CustomerDAO(Connection mConn) {
        this.mConn = mConn;
    }

    public Customer retrieveCustomer(long custId) {
        Customer customer = null;
        try {
            ResultSet results = mConn.createStatement().executeQuery("SELECT EMAIL, PASSWORD, FIRST_NAME, LAST_NAME,\n"
                    + "ADDRESS, JOB, PHONE_NUMBER, DATE_OF_BIRTH, CREDIT_LIMIT, WISHLIST\n"
                    + "FROM DEALTIME.CUSTOMERS\n"
                    + "WHERE CUSTOMER_ID = " + custId);
            while (results.next()) {
                customer = new Customer();
                customer.setCustId(custId);
                customer.setCustEmail(results.getString("EMAIL"));
                customer.setCustPassword(results.getString("PASSWORD"));
                customer.setCustFirstName(results.getString("FIRST_NAME"));
                customer.setCustLastName(results.getString("LAST_NAME"));
                customer.setCustAddress(results.getString("ADDRESS"));
                customer.setCustJob(results.getString("JOB"));
                customer.setCustMobileNumber(results.getString("PHONE_NUMBER"));
                if (results.getDate("DATE_OF_BIRTH") == null) {
                    customer.setCustDateOfBirth(null);
                } else {
                    customer.setCustDateOfBirth(results.getDate("DATE_OF_BIRTH").toLocalDate());
                }
                customer.setCustCreditLimit(results.getDouble("CREDIT_LIMIT"));
                customer.setCustWishList(results.getString("WISHLIST"));
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
        return customer;
    }

    public Customer retrieveCustomer(String email, String password) {
        Customer customer = null;
        try {
            ResultSet results = mConn.createStatement().executeQuery("SELECT CUSTOMER_ID, FIRST_NAME, LAST_NAME, ADDRESS, JOB,\n"
                    + "PHONE_NUMBER, DATE_OF_BIRTH, CREDIT_LIMIT, WISHLIST\n"
                    + "FROM DEALTIME.CUSTOMERS\n"
                    + "WHERE UPPER(EMAIL) = UPPER('" + email + "')\n"
                    + "AND UPPER(PASSWORD) = UPPER('" + password + "')");
            if (results.next()) {
                customer = new Customer();
                customer.setCustEmail(email);
                customer.setCustPassword(password);
                customer.setCustId(results.getLong("CUSTOMER_ID"));
                customer.setCustFirstName(results.getString("FIRST_NAME"));
                customer.setCustLastName(results.getString("LAST_NAME"));
                customer.setCustAddress(results.getString("ADDRESS"));
                customer.setCustJob(results.getString("JOB"));
                customer.setCustMobileNumber(results.getString("PHONE_NUMBER"));
                if (results.getDate("DATE_OF_BIRTH") == null) {
                    customer.setCustDateOfBirth(null);
                } else {
                    customer.setCustDateOfBirth(results.getDate("DATE_OF_BIRTH").toLocalDate());
                }
                customer.setCustCreditLimit(results.getDouble("CREDIT_LIMIT"));
                customer.setCustWishList(results.getString("WISHLIST"));
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
        return customer;
    }

    public List<Customer> retrieveAllCustomers() {
        ArrayList<Customer> customers = new ArrayList();
        try {
            ResultSet results = mConn.createStatement().executeQuery("SELECT CUSTOMER_ID, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME,\n"
                    + "ADDRESS, JOB, PHONE_NUMBER, DATE_OF_BIRTH, CREDIT_LIMIT, WISHLIST\n"
                    + "FROM DEALTIME.CUSTOMERS");
            while (results.next()) {
                Customer customer = new Customer();
                customer.setCustId(results.getLong("CUSTOMER_ID"));
                customer.setCustEmail(results.getString("EMAIL"));
                customer.setCustPassword(results.getString("PASSWORD"));
                customer.setCustFirstName(results.getString("FIRST_NAME"));
                customer.setCustLastName(results.getString("LAST_NAME"));
                customer.setCustAddress(results.getString("ADDRESS"));
                customer.setCustJob(results.getString("JOB"));
                customer.setCustMobileNumber(results.getString("PHONE_NUMBER"));
                if (results.getDate("DATE_OF_BIRTH") == null) {
                    customer.setCustDateOfBirth(null);
                } else {
                    customer.setCustDateOfBirth(results.getDate("DATE_OF_BIRTH").toLocalDate());
                }
                customer.setCustCreditLimit(results.getDouble("CREDIT_LIMIT"));
                customer.setCustWishList(results.getString("WISHLIST"));
                customers.add(customer);
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
        return customers;
    }

    private boolean checkEmailExistance(String email) {
        boolean result = false;
        try {
            ResultSet rSet = mConn.createStatement().executeQuery("SELECT COUNT(*) FROM DEALTIME.CUSTOMERS\n"
                    + "WHERE UPPER(EMAIL) = UPPER('" + email + "')");
            if (rSet.next()) {
                if (rSet.getInt(1) > 0) {
                    result = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = false;
        }
        return result;
    }

    public String insertCustomer(Customer cust) {
        String result;
        boolean isCustomerExisting = checkEmailExistance(cust.getCustEmail());
        try {
            if (isCustomerExisting) {
                result = EXISTING_EMAIL;
            } else {
                PreparedStatement stmt = mConn.prepareStatement("INSERT INTO DEALTIME.CUSTOMERS (CUSTOMER_ID, EMAIL, PASSWORD,\n"
                        + "FIRST_NAME, LAST_NAME, ADDRESS, JOB, PHONE_NUMBER, DATE_OF_BIRTH, CREDIT_LIMIT, WISHLIST)\n"
                        + "VALUES ( CUSTOMERS_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                stmt.setString(1, cust.getCustEmail());
                stmt.setString(2, cust.getCustPassword());
                stmt.setString(3, cust.getCustFirstName());
                stmt.setString(4, cust.getCustLastName());
                stmt.setString(5, cust.getCustAddress());
                stmt.setString(6, cust.getCustJob());
                stmt.setString(7, cust.getCustMobileNumber());
                if (cust.getCustDateOfBirth() != null) {
                    stmt.setDate(8, Date.valueOf(cust.getCustDateOfBirth()));
                } else {
                    stmt.setNull(8, java.sql.Types.DATE);
                }
                stmt.setDouble(9, cust.getCustCreditLimit());
                stmt.setString(10, cust.getCustWishList());
                stmt.execute();
                result = SUCCESSFUL_INSERT;
            }
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

    private boolean checkEmailRepetition(Customer customer) {
        boolean result = false;
        try {
            ResultSet rSet = mConn.createStatement().executeQuery("SELECT COUNT(*) FROM DEALTIME.CUSTOMERS\n"
                    + "WHERE UPPER(EMAIL) = UPPER('" + customer.getCustEmail() + "')\n"
                    + "AND CUSTOMER_ID != " + customer.getCustId());
            if (rSet.next()) {
                if (rSet.getInt(1) > 0) {
                    result = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = false;
        }
        return result;
    }

    public String updateCustomer(Customer cust) {
        String result;
        boolean isCustomerExisting = checkEmailRepetition(cust);
        try {
            if (isCustomerExisting) {
                result = EXISTING_EMAIL;
            } else {
                PreparedStatement stmt = mConn.prepareStatement("UPDATE DEALTIME.CUSTOMERS\n"
                        + "SET EMAIL = ?, PASSWORD = ?, FIRST_NAME = ?,  LAST_NAME = ?, ADDRESS = ?,  JOB = ?,\n"
                        + "PHONE_NUMBER = ?, DATE_OF_BIRTH = ?, CREDIT_LIMIT = ?, WISHLIST = ?\n"
                        + "WHERE CUSTOMER_ID = " + cust.getCustId());
                stmt.setString(1, cust.getCustEmail());
                stmt.setString(2, cust.getCustPassword());
                stmt.setString(3, cust.getCustFirstName());
                stmt.setString(4, cust.getCustLastName());
                stmt.setString(5, cust.getCustAddress());
                stmt.setString(6, cust.getCustJob());
                stmt.setString(7, cust.getCustMobileNumber());
                if (cust.getCustDateOfBirth() != null) {
                    stmt.setDate(8, Date.valueOf(cust.getCustDateOfBirth()));
                } else {
                    stmt.setNull(8, java.sql.Types.DATE);
                }
                stmt.setDouble(9, cust.getCustCreditLimit());
                stmt.setString(10, cust.getCustWishList());
                stmt.execute();
                result = SUCCESSFUL_UPDATE;
            }
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

    private boolean deleteCustomerOrders(Customer customer) {
        boolean result = false;
        try {
            PreparedStatement stmt = mConn.prepareStatement("DELETE DEALTIME.ORDERS WHERE CUSTOMER_ID = " + customer.getCustId());
            stmt.execute();
            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public String deleteCustomer(Customer customer) {
        String result;
        boolean isCustomerOrdersDeleted = deleteCustomerOrders(customer);
        try {
            if (isCustomerOrdersDeleted) {
                PreparedStatement stmt = mConn.prepareStatement("DELETE DEALTIME.CUSTOMERS WHERE CUSTOMER_ID = " + customer.getCustId());
                stmt.execute();
                result = SUCCESSFUL_DELETE;
            } else {
                result = DELETING_CUSTOMER_ERROR;
            }
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
}
