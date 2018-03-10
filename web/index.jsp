<!-- just for testing -->
<form method="GET" action="userProfile">
    <input type="submit" value="Go to user profile"/>
</form>
<form method="GET" action="headerFooterServlet">
    <input type="submit" value="Go to header"/>
</form>
<form method="POST" action="headerFooterServlet">
    <input type="submit" value="Go to footer"/>
</form>
<form method="GET" action="userCartControl">
    <input type="submit" value="Go to user Cart"/>
</form>
<form method="GET" action="paginationServlet">
    <input type="submit" value="Go to pagination"/>
</form>
<form method="GET" action="productDetails">
    <input type="submit" value="Go to product details"/>
</form>

<jsp:include page="WEB-INF/view/header.jsp"/>

<%@page contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="robots" content="all,follow">
        <meta name="googlebot" content="index,follow,snippet,archive">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Obaju e-commerce template">
        <meta name="author" content="Ondrej Svestka | ondrejsvestka.cz">
        <meta name="keywords" content="">

        <title>
            DealTime
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
                                        <a href="#">Men</a>
                                        <ul>
                                            <li><a href="category?cat=category1">Category 1</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="active">
                                        <a href="#">Ladies</a>
                                        <ul>
                                            <li><a href="category?cat=category2">Category 2</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a href="#">Kids</a>
                                        <ul>
                                            <li><a href="category?cat=category3">Category 3</a>
                                            </li>
                                        </ul>
                                    </li>

                                </ul>

                            </div>

                        </div>

                    </div>

                    <div class="col-md-9">

                        <div class="box info-bar">
                            <div class="row">
                                <div class="col-sm-12 col-md-4 products-showing">
                                    Showing <strong>5</strong> of <strong>${sessionScope.AllproductsNumber}</strong> products
                                </div>

                                <div class="col-sm-12 col-md-8  products-number-sort">
                                    <div class="row">
                                        <form class="form-inline">

                                            <div class="col-md-6 col-sm-6">
                                                <div class="products-sort-by">
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row products">

                            <!--                            <div class="col-md-4 col-sm-6">
                                                            <div class="product">
                                                                <div class="flip-container">
                                                                    <div class="flipper">
                                                                        <div class="front">
                                                                            <a href="productDetails?productId=${product.productId}">
                                                                                <img src="res/img/product1.jpg" alt="" class="img-responsive">
                                                                            </a>
                                                                        </div>
                                                                        <div class="back">
                                                                            <a href="detail.html">
                                                                                <img src="res/img/product1_2.jpg" alt="" class="img-responsive">
                                                                            </a>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <a href="detail.html" class="invisible">
                                                                    <img src="res/img/product1.jpg" alt="" class="img-responsive">
                                                                </a>
                                                                <div class="text">
                                                                    <h3><a href="detail.html">Fur coat with very but very very long name</a></h3>
                                                                    <p class="price">$143.00</p>
                                                                    <p class="buttons">
                                                                        <a href="detail.html" class="btn btn-default">View detail</a>
                                                                        <a href="basket.html" class="btn btn-primary"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                                                    </p>
                                                                </div>
                                                                 /.text 
                                                            </div>
                                                             /.product 
                                                        </div>-->
                            <c:forEach items="${sessionScope.productsList}" var="product">
                                <div class="col-md-4 col-sm-6">
                                    <div class="product">
                                        <div class="flip-container">
                                            <div class="flipper">
                                                <div class="front">
                                                    <a href="productDetails?productId=${product.productId}">  
                                                        <img src="res/products_images/${product.productId}.jpg"  class="img-responsive" alt="">
                                                    </a>
                                                </div>
                                                <div class="back">
                                                    <a href="productDetails?productId=${product.productId}">
                                                        <img src="res/products_images/${product.productId}.jpg"  class="img-responsive">
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                        <a href="detail.html" class="invisible">
                                            <img src="res/products_images/${product.productId}.jpg" alt="" class="img-responsive">
                                        </a>
                                        <div class="text">
                                            <h3> <a href="productDetails?productId=${product.productId}"> ${product.productName}</a></h3>
                                            <p class="price">$ ${product.productPrice} </p>
                                            <p class="buttons">
                                                <a href="detail.html" class="btn btn-default">View detail</a>
                                                <!--<a href="basket.html" class="btn btn-primary"><i class="fa fa-shopping-cart"></i>Add to cart</a>-->
                                            </p>

                                        </div>
                                        <!-- /.text -->
                                    </div>
                                    <!-- /.product -->
                                </div>


                            </c:forEach>


                            <!-- /.col-md-4 -->
                        </div>
                        <!-- /.products -->

                        <div class="pages">

                            <ul class="pagination">
                                <li><a >&laquo;</a>
                                </li>
                                <c:forEach begin="1" end="${sessionScope.AllproductsNumber}" step="5" varStatus="loop">
                                    <%--<c:out value="${loop.count}"/>--%>
                                    <li class="active">
                                        <a href="dealTime?page=${loop.count}">${loop.count}</a>
                                    </li>
                                </c:forEach>
                                <li><a >&raquo;</a>
                                </li>
                            </ul>
                        </div>


                    </div>
                    <!-- /.col-md-9 -->
                </div>
                <!-- /.container -->
            </div>
            <!-- /#content -->

        </div>
        <!-- /#all -->

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

<jsp:include page="WEB-INF/view/footer.jsp"/>
