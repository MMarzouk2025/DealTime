package com.deal.servlet;

import com.deal.base.model.Product;
import com.deal.control.DbHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mahmoud.Marzouk
 */
public class ProductsPanelControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Product> allProducts = (ArrayList<Product>) DbHandler.getProductDAO().retrieveAllProduct();
        request.setAttribute("products", allProducts);
        request.getRequestDispatcher("/WEB-INF/view/productsAdministration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operationType = request.getParameter("oper");
        switch (operationType) {
            case "delete" :
                long productId = Long.parseLong(request.getParameter("pId"));
                String result = DbHandler.getProductDAO().deleteProduct(productId);
                response.getWriter().write(result);
                break;
        }
    }
    
}
