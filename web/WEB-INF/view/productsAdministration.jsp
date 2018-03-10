<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="robots" content="all,follow">
        <meta name="googlebot" content="index,follow,snippet,archive">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <meta name="keywords" content="">

        <title>Products</title>

        <!-- styles -->
        <link href="../res/css/font-awesome.css" rel="stylesheet">
        <link href="../res/css/bootstrap.min.css" rel="stylesheet">
        <link href="../res/css/animate.min.css" rel="stylesheet">
        <link href="../res/css/owl.carousel.css" rel="stylesheet">
        <link href="../res/css/owl.theme.css" rel="stylesheet">

        <!-- theme stylesheet -->
        <link href="../res/css/style.default.css" rel="stylesheet" id="theme-stylesheet">

        <!-- your stylesheet with modifications -->
        <link href="../res/css/custom.css" rel="stylesheet">

        <script src="../res/js/respond.min.js"></script>

        <link rel="shortcut icon" href="favicon.png">
    </head>

    <body>
        <style type="text/css">
            html, body {
                max-width: 100%;
                overflow-x: hidden;
            }
            
            #categoriesList option{
                font-size: 20;
            }
        </style>

        <div class="container">
            <div class="col-md-9" id="customer-order">
                <div>
                    <div class="form-group" style="display: inline-block; width: 70%;">
                        <label for="category" style="font-weight: bold;">Category </label>
                        <select id="categoriesList" class="form-control" style="font-size: medium; 
                                margin-bottom: 1.5em; margin-top: 0.25em; float: top; text-align: center;">
                            <option selected="selected" disabled>Select Category ...</option>
                            <c:forEach items="${requestScope.categories}" var="category">
                                <option value="${category.getCategoryId()}">${category.getCategoryName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div style="display: inline-block;">
                        <button class="btn btn-success" style="margin-bottom: 0.45em; margin-left: 2em;">
                            <i class="fa fa-plus-square-o"></i>
                        </button>
                        <button class="btn btn-danger" style="margin-bottom: 0.45em; margin-left: 1.3em;">
                            <i class="fa fa-trash-o"></i>
                        </button>
                    </div>
                </div>
                <div class="table-responsive" style="width: 88%; text-align: center; margin-top: 1em;">
                    <table class="table" >
                        <thead style="display:table; width:100%; table-layout:fixed;">
                            <tr>
                                <th colspan="2" style="width: 35%">Product</th>
                                <th style="text-align: center; width: 10%">Quantity</th>
                                <th style="text-align: center; width: 15%">Unit price</th>
                                <th style="text-align: center; width: 18%">Category</th>
                                <th style="text-align: center; width: 11%"></th>
                                <th style="text-align: center; width: 11%"></th>
                            </tr>
                        </thead>
                        <tbody style="display: block; overflow-y:scroll; max-height:45em; overflow: auto;">
                            <c:forEach items="${requestScope.products}" var="product">
                                <tr style="display:table; width:100%; table-layout:fixed;" id="${product.getProductId()}">
                                    <td style="text-align: left; width: 14%;">
                                        <img src="res/products_images/${product.getProductId()}.jpg" alt="No Image">
                                    </td>
                                    <td style="text-align: left; text-overflow: ellipsis; overflow: hidden; 
                                        white-space: nowrap; padding-left: 0;  width: 21%;">
                                        <a title="${product.getProductName()}">${product.getProductName()}</a>
                                    </td>
                                    <td style="width: 10%">${product.getAvailableQuantity()}</td>
                                    <td style="width: 15%">$${product.getProductPrice()}</td>
                                    <td style="width: 18%">${product.getProductCategory().getCategoryName()}</td>
                                    <td style="text-align: right; padding-right: 0; width: 11%;">
                                        <button class="btn btn-primary">
                                            <i class="fa fa-edit"></i>
                                        </button>
                                    </td>
                                    <td style="padding-left: 0; width: 11%;">
                                        <button class="btn btn-danger" 
                                                onclick="removeProduct($(this).parent().parent().attr('id'))">
                                            <i class="fa fa-trash-o"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>    
                        </tbody>
                    </table>
                    <button class="btn btn-success" style="width: 100%; font-size: medium; 
                            margin-bottom: 1em; margin-top: 0.5em; float: bottom;">
                        <i class="fa fa-plus-circle"></i> Add new product
                    </button>
                </div>
                <!-- /.table-responsive -->

            </div>
        </div>
        <!-- /.container -->

        <!-- *** SCRIPTS TO INCLUDE ***
     _________________________________________________________ -->
        <script src="../res/js/jquery-1.11.0.min.js"></script>
        <script src="../res/js/bootstrap.min.js"></script>
        <script src="../res/js/jquery.cookie.js"></script>
        <script src="../res/js/waypoints.min.js"></script>
        <script src="../res/js/modernizr.js"></script>
        <script src="../res/js/bootstrap-hover-dropdown.js"></script>
        <script src="../res/js/owl.carousel.min.js"></script>
        <script src="../res/js/front.js"></script>

    </body>

</html>
