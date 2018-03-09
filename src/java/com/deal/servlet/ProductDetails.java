/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deal.servlet;

import com.deal.base.model.Customer;
import com.deal.base.model.Order;
import com.deal.base.model.Product;
import com.deal.control.DbHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ElsOoObkey
 */
public class ProductDetails extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //  response.setContentType("text/html;charset=UTF-8");
        Product product = DbHandler.getProductDAO().retrieveProduct(1);
        request.setAttribute("product", product);
//        Customer cutomer=DbHandler.getCustomerDAO().retrieveCustomer(1);
//        List<Order> orders=DbHandler.getOrderDAO().retrieveCustomerOrders(cutomer);
//        request.setAttribute("orders", orders);

        request.getRequestDispatcher("WEB-INF/view/productDetailsPanel.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Product Details Servlet";
    }

}
