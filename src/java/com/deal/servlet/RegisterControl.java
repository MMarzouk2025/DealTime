/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deal.servlet;

import com.deal.base.control.CustomerDAO;
import com.deal.base.model.Customer;
import com.deal.control.DbHandler;
import com.deal.utility.Validations;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DR Gamal
 */
public class RegisterControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/logintry.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //get parameters from form
        String fname = request.getParameter("userfirstnameregister"); 
        String lname = request.getParameter("userlastnameregister");
        String phone = request.getParameter("phonenumregister");
        String password = request.getParameter("passwordregister");
        String creditlimit = request.getParameter("usercreditlimitregister");
        String birthday = request.getParameter("birthdayregister"); 
        String job = request.getParameter("job"); 
        String email = request.getParameter("emailregister"); 
        String address = request.getParameter("addressregister"); 
        if(Validations.emailIsValid(email) && Validations.nameIsValid(fname) && Validations.nameIsValid(lname) && Validations.passIsValid(password)){
        // pass it to customer object
            Customer customer = new Customer();
            customer.setCustFirstName(fname);
            customer.setCustLastName(lname);
            customer.setCustMobileNumber(phone);
            customer.setCustPassword(password);
            customer.setCustCreditLimit(Double.parseDouble(creditlimit));
            customer.setCustEmail(email);
            customer.setCustJob(job);
            customer.setCustDateOfBirth(LocalDate.parse(birthday));
            customer.setCustAddress(address);
            //pass it to db
            CustomerDAO customerDao = DbHandler.getCustomerDAO();
            String result = customerDao.insertCustomer(customer);
            if(result.equals(CustomerDAO.EXISTING_EMAIL)){
                System.out.println(result);
                request.getRequestDispatcher("WEB-INF/view/logintry.jsp").forward(request, response);

            }
            else if(result.equals(CustomerDAO.SUCCESSFUL_INSERT)){
                System.out.println(result);
                HttpSession session = request.getSession(true);
                session.setAttribute("loggedInUser", customer);
                 request.getRequestDispatcher("WEB-INF/view/userOrdersControlPanel.jsp").forward(request, response);

            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
