/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deal.servlet;

import com.deal.base.control.CustomerDAO;
import com.deal.base.control.OrderDAO;
import com.deal.base.control.ProductDAO;
import com.deal.base.model.Customer;
import com.deal.base.model.Order;
import com.deal.base.model.Product;
import com.deal.control.DbHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nagib
 */
public class CheckOutCart extends HttpServlet {

    HttpSession session;
    ArrayList<Product> productsList = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession(true);
        Customer customer = (Customer) session.getAttribute("loggedInUser");
        if (customer != null) {
            boolean flag1 = true;
            OrderDAO orderDAO = DbHandler.getOrderDAO();
            ArrayList<Order> customerOrderList = orderDAO.retrieveCustomerOrders(customer);
            for (Order order : customerOrderList) {
                if (order.getOrderProduct().getAvailableQuantity() < order.getQuantity()) {
                    System.out.println("there is one");
                    flag1 = false;
                    break;
                }
            }
            if (!flag1) {
                response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);

            } else {
                orderDAO = DbHandler.getOrderDAO();
                orderDAO.updateCustomerOrders((int) customer.getCustId());
                CustomerDAO CustomerDAOObj = DbHandler.getCustomerDAO();
                customer.setCustCreditLimit(Double.parseDouble(request.getParameter("newCridet")));
                CustomerDAOObj.updateCustomer(customer);
                session.setAttribute("loggedInUser", customer);
                session.setAttribute("CustomerOrderNo", 0);
                for (Order order : customerOrderList) {
                    ProductDAO productDAOobject = DbHandler.getProductDAO();
                    Product p =order.getOrderProduct();
                    System.out.println("p.getAvailableQuantity() "+ p.getAvailableQuantity());
                    System.out.println("order.getQuantity() "+ order.getQuantity());
                    p.setAvailableQuantity(p.getAvailableQuantity()-order.getQuantity());
                    productDAOobject.updateProduct(p);

                }
            }

        }
    }

}


