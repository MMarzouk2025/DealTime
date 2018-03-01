package com.deal.control;

import com.deal.base.model.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Marzouk1@gmail.com", "123456", "Mahmoud1", "Marzouk1", 120, "0105566447"));
        customers.add(new Customer("Marzouk2@gmail.com", "123456", "Mahmoud2", "Marzouk2", 120, "0105566447"));
        customers.add(new Customer("Marzouk3@gmail.com", "123456", "Mahmoud3", "Marzouk3", 120, "0105566447"));
        customers.add(new Customer("Marzouk4@gmail.com", "123456", "Mahmoud4", "Marzouk4", 120, "0105566447"));
        customers.add(new Customer("Marzouk5@gmail.com", "123456", "Mahmoud5", "Marzouk5", 120, "0105566447"));
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("WEB-INF/view/adminControlPanel.jsp").forward(request, response);
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
        return "Administration";
    }

}
