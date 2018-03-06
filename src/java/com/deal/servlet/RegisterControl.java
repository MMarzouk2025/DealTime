/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deal.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        String name = request.getParameter("usernameregister"); //tmam
        String password = request.getParameter("passwordregister"); // tamam
        String birthday = request.getParameter("birthdayregister"); //tmam
        String job = request.getParameter("job"); //tmam
        String email = request.getParameter("emailregister"); //tmam
        String address = request.getParameter("addressregister"); //tmam
        String interest = request.getParameter("interestregister");//tmam
        out.println(name + " " + password + " " + birthday + " " + job + " " + email + " " + address + " " + interest);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
