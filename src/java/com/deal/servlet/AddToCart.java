package com.deal.servlet;

import com.deal.base.control.OrderDAO;
import com.deal.base.model.Customer;
import com.deal.base.model.Order;
import com.deal.base.model.Product;
import com.deal.control.DbHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mahmoud.Marzouk
 */
public class AddToCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String param = (String) parameterNames.nextElement();
            System.out.println(param);
        }
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("dealTime");

        } else {

            Customer customer = (Customer) session.getAttribute("loggedInUser");
            String productId = request.getParameter("productDetailsId");
            if (customer != null && productId != null) {
                OrderDAO orderDAO = DbHandler.getOrderDAO();
                OrderDAO orderDAO2 = DbHandler.getOrderDAO();
                ArrayList<Order> existOrders = orderDAO.getOrderByProduct(customer, Integer.parseInt(productId));
                System.out.println("the list size is " + existOrders.size());
                if (existOrders.size() == 0) {
                    orderDAO2.insertOrder(new Order(customer, new Product(Long.parseLong(productId)), 1, 'c'));

                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);

                }

            } else {
                response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);

            }

        }
    }

}
