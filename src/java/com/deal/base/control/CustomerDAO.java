package com.deal.base.control;

import com.deal.base.model.Customer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    Connection mConn;

    public CustomerDAO(Connection mConn) {
        this.mConn = mConn;
    }

    public Customer retrieveCustomer(String email, String password) { // one case to handle
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
                customer.setCustDateOfBirth(results.getDate("DATE_OF_BIRTH").toLocalDate());
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
                customer.setCustDateOfBirth(results.getDate("DATE_OF_BIRTH").toLocalDate());
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

    public boolean insertCustomer(Customer cust) { // 3 cases to handle
        boolean result = false;
        System.out.println("asdasdasa asdasda");

        try {
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

            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = false;
        } finally {
            try {
                mConn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }

}
