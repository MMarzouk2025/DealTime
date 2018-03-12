/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deal.servlet;

import com.deal.base.control.CustomerDAO;
import com.deal.base.model.Admin;
import com.deal.base.model.Customer;
import com.deal.control.DbHandler;
import com.deal.utility.Validations;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DR Gamal
 */
public class LogInControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // get input from user
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            // insert in database
            if (Validations.emailIsValid(email) && Validations.passIsValid(password)) {
                Customer customer;
                CustomerDAO customerDao = DbHandler.getCustomerDAO();
                customer = customerDao.retrieveCustomer(email, password);
                Admin admin = DbHandler.getAdminDAO().retrieveAdmin(email, password);

                if (customer != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("loggedInUser", customer);
                    session.setAttribute("userType", "c");
                    //redirect to customers page
                    request.getRequestDispatcher("WEB-INF/view/userOrdersControlPanel.jsp").forward(request, response);
                } else if (admin != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("loggedInUser", admin);
                    session.setAttribute("userType", "a");
                    //redirect to admin page
                    // request.getRequestDispatcher("administration").forward(request, response);
                    response.sendRedirect("/DealTime/administration");
                } else {
                    System.out.println("user doesn't exist");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } else {
                System.out.println("hello");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
