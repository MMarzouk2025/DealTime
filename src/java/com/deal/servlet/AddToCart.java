package com.deal.servlet;

import com.deal.base.control.OrderDAO;
import com.deal.base.model.Customer;
import com.deal.base.model.Order;
import com.deal.base.model.Product;
import com.deal.control.DbHandler;
import java.io.IOException;
import java.io.PrintWriter;
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
        request.getRequestDispatcher("/WEB-INF/view/products.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("index.jsp");

        } else {
            String loggedIn = (String) session.getAttribute("loggedIn");
            if (!loggedIn.equals("true")) {
                response.sendRedirect("index.jsp");
            }
            Customer customer = (Customer) session.getAttribute("currentCustomer");
            Product product = (Product) session.getAttribute("");

            if (customer != null && product != null) {
                Order order = new Order(customer, product, 1, 'c');
                OrderDAO orderDAO = DbHandler.getOrderDAO();
                orderDAO.insertOrder(order);
            }

        }
    }

}
