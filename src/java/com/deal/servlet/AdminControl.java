package com.deal.servlet;

import com.deal.base.model.Customer;
import com.deal.control.DbHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminControl extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        List<Customer> customers = DbHandler.getCustomerDAO().retrieveAllCustomers();
        System.out.println(customers.get(0));
        DbHandler.getCustomerDAO().insertCustomer(new Customer("zooka", "asdas", "asdasdfd", "asdfsdfsd", "sgsdf gdgdf", "ai 7aga", 
            "011162521478", LocalDate.of(1992, 1, 6), 320.50, "asdfasd fasdfs"));
        request.getRequestDispatcher("WEB-INF/view/adminControlPanel.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // post action
    }
    
    @Override
    public String getServletInfo() {
        return "Administration";
    }
    
}
