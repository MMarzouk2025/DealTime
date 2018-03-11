package com.deal.servlet;

import com.deal.base.model.Category;
import com.deal.base.model.Product;
import com.deal.control.DbHandler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductManage extends HttpServlet {
    
    // get for updating product info
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    // post for new product insertion
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productName = request.getParameter("productName");
        String productDesc = request.getParameter("productDescription");
        System.out.println(request.getParameter("productName"));
        int price = Integer.parseInt(request.getParameter("productPrice"));
        int quantity = Integer.parseInt(request.getParameter("productQuantity"));
        System.out.println(request.getParameter("productCategoryField"));
        Category category = DbHandler.getCategoryDAO()
                .retrieveCategory(Long.parseLong(request.getParameter("productCategoryField")));
        String productImgFileName = request.getParameter("pic");
        
        Product product = new Product(productName);
        product.setProductDesc(productDesc);
        product.setProductPrice(price);
        product.setAvailableQuantity(quantity);
        product.setProductCategory(category);
        product.setProductImageFileName(productImgFileName);
        
        String resultProductId = DbHandler.getProductDAO().insertProduct(product);

        response.getWriter().write(resultProductId);
    }
    
}
