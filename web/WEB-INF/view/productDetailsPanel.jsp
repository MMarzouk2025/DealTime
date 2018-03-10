<%-- 
    Document   : productControlPanel
    Created on : Mar 3, 2018, 9:35:14 AM
    Author     : ElsOoObkey
--%>

<jsp:include page="header.jsp" />

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <meta name="robots" content="all,follow">
        <meta name="googlebot" content="index,follow,snippet,archive">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Obaju e-commerce template">
        <meta name="author" content="Ondrej Svestka | ondrejsvestka.cz">
        <meta name="keywords" content="">

        <title>
            Obaju : Product details
        </title>

        <meta name="keywords" content="">

        <link href='http://fonts.googleapis.com/css?family=Roboto:400,500,700,300,100' rel='stylesheet' type='text/css'>

        <!-- styles -->
        <link href="res/css/font-awesome.css" rel="stylesheet">
        <link href="res/css/bootstrap.min.css" rel="stylesheet">
        <link href="res/css/animate.min.css" rel="stylesheet">
        <link href="res/css/owl.carousel.css" rel="stylesheet">
        <link href="res/css/owl.theme.css" rel="stylesheet">

        <!-- theme stylesheet -->
        <link href="res/css/style.default.css" rel="stylesheet" id="theme-stylesheet">

        <!-- your stylesheet with modifications -->
        <link href="res/css/custom.css" rel="stylesheet">

        <script src="res/js/respond.min.js"></script>

        <link rel="shortcut icon" href="favicon.png">



    </head>

    <body>

        <div id="all">

            <div id="content">
                <div class="container">

                    <div class="col-md-3">
                        <!-- *** MENUS AND FILTERS ***
     _________________________________________________________ -->
                        <div class="panel panel-default sidebar-menu">

                            <div class="panel-heading">
                                <h3 class="panel-title">Categories</h3>
                            </div>

                            <div class="panel-body">
                                <ul class="nav nav-pills nav-stacked category-menu">
                                    <li>
                                        <a href="category.html">Men <span class="badge pull-right">42</span></a>
                                        <ul>
                                            <li><a href="category.html">T-shirts</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="active">
                                        <a href="category.html">Ladies  <span class="badge pull-right">123</span></a>
                                        <ul>
                                            <li><a href="category.html">T-shirts</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a href="category.html">Kids  <span class="badge pull-right">11</span></a>
                                        <ul>
                                            <li><a href="category.html">T-shirts</a>
                                            </li>
                                        </ul>
                                    </li>

                                </ul>

                            </div>

                        </div>

                        <!-- *** MENUS AND FILTERS END *** -->

                    </div>

                    <div class="col-md-9">

                        <div class="row" id="productMain">
                            <div class="col-sm-6">
                                <div id="mainImage">
                                    <img src="res/products_images/${requestScope.product.productId}.jpg" alt="" class="img-responsive">
                                </div>

                            </div>
                            <div class="col-sm-6">
                                <div class="box">
                                    <h1 class="text-center">${requestScope.product.productName}</h1>

                                    <p class="price">$ ${requestScope.product.productPrice}</p>

                                    <p class="text-center buttons">
                                        <a href="basket.html" class="btn btn-primary"><i class="fa fa-shopping-cart"></i> Add to cart</a> 
                                        <a href="basket.html" class="btn btn-default"><i class="fa fa-heart"></i> Add to wishlist</a>
                                    </p>


                                </div>

                            </div>

                        </div>


                        <div class="box" id="details">
                            <p>
                            <h4>Product details</h4>
                            <p>${requestScope.product.productDesc}</p>

                        </div>

                    </div>
                    <!-- /.col-md-9 -->
                </div>
                <!-- /.container -->
            </div>
            <!-- /#content -->

        </div>
        <!-- /#all -->




        <!-- *** SCRIPTS TO INCLUDE ***
     _________________________________________________________ -->
        <script src="res/js/jquery-1.11.0.min.js"></script>
        <script src="res/js/bootstrap.min.js"></script>
        <script src="res/js/jquery.cookie.js"></script>
        <script src="res/js/waypoints.min.js"></script>
        <script src="res/js/modernizr.js"></script>
        <script src="res/js/bootstrap-hover-dropdown.js"></script>
        <script src="res/js/owl.carousel.min.js"></script>
        <script src="res/js/front.js"></script>






    </body>


</html>

<jsp:include page="footer.jsp" />