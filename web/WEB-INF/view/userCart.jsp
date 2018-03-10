<%-- 
    Document   : userCart
    Created on : Mar 1, 2018, 4:35:15 AM
    Author     : nagib
--%>

<%
//    response.setHeader("Cache-Control", "no-cache");
//    response.setHeader("Pragma", "no-cache");
//    response.setDateHeader("Expires", -1);
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>

        <link href='http://fonts.googleapis.com/css?family=Roboto:400,500,700,300,100' rel='stylesheet' type='text/css'>

        <!-- styles -->
        <link href="res/css/font-awesome.css" rel="stylesheet">
        <link href="res/css/bootstrap.min.css" rel="stylesheet">
        <link href="res/css/animate.min.css" rel="stylesheet">
        <link href="res/css/owl.carousel.css" rel="stylesheet">
        <link href="res/css/owl.theme.css" rel="stylesheet">
        <style>
            .myItem { 
                color:#4fbfa8;
            }
            .myItem:hover{cursor: pointer;}
            .mynumber{
                width: 30em;
            }
        </style>

        <!-- theme stylesheet -->
        <link href="res/css/style.default.css" rel="stylesheet" id="theme-stylesheet">

        <!-- your stylesheet with modifications -->
        <link href="res/css/custom.css" rel="stylesheet">

        <script src="res/js/respond.min.js"></script>
        <script>
            var changedOrders = {"changedOrders": []};
            var updateCartReq = null;
            var rowsCounter = ${Items.size()};
            function updateCart(btn) {
                var jsonData = {"name": "name", "pass": "password"};
                console.log(changedOrders);
                $.ajax({
                    type: 'POST',
                    url: 'UpdateCart', //calling servlet      
                    cache: false,
                    data: changedOrders,
                    success: function () {
                        sessionStorage.removeItem('Items');
                        console.log("success:");
                        document.getElementById("updateCartBtn").disabled = true;
                        document.getElementById("checkoutBtn").disabled = false;
                    },
                    error: function (xhr, ajaxOptions) {
                        console.log("error:")
                    },

                });
            }

            function  updateQuantity(btn, orderProductPrice, availableQuantity) {
                if (btn.value < 0 || btn.value > availableQuantity) {
                    btn.value = 1;

                } else {
                    allchangedOrders = changedOrders.changedOrders;
                    orderIndex = allchangedOrders.findIndex(order => order.orderId == btn.getAttribute('id'));
                    window.console.log(orderIndex);
                    if (orderIndex == -1) {
                        allchangedOrders.push({"orderId": btn.getAttribute('id'), "quantity": btn.value});
                    } else {
                        allchangedOrders[orderIndex].quantity = btn.value;
                    }
                    document.getElementById("updateCartBtn").disabled = false;
                    document.getElementById("checkoutBtn").disabled = true;
                    priceelementid = "orderPrice" + btn.getAttribute('id')
                    oldprice = document.getElementById(priceelementid).innerHTML;
                    newprice = btn.value * orderProductPrice
                    diffPrice = parseFloat(newprice) - parseFloat(oldprice);
                    totalPrice = document.getElementById("cartTotalPrice1").innerHTML;
                    newTotalPrice = parseFloat(diffPrice) + parseFloat(totalPrice) + 0.0;
                    document.getElementById(priceelementid).innerHTML = newprice;
                    document.getElementById("cartTotalPrice1").innerHTML = newTotalPrice;
                    document.getElementById("cartTotalPrice2").innerHTML = newTotalPrice;
                    document.getElementById("cartTotalPrice3").innerHTML = newTotalPrice;
                    window.console.log(allchangedOrders);
                    console.log("Quantity : " + btn.value + "   id is : " + btn.getAttribute('id'));
                }
            }

            function  deleteRow(btn, orderId1) {
                var row = btn.parentNode.parentNode;
                document.getElementById("updateCartBtn").disabled = false;
                document.getElementById("checkoutBtn").disabled = true;
                var priceelementid = "orderPrice" + orderId1;
                var oldprice = document.getElementById("cartTotalPrice1").innerHTML;
                var newprice = document.getElementById(priceelementid).innerHTML;
                var diffPrice = parseFloat(oldprice) - parseFloat(newprice) + 0.0;
                document.getElementById("cartTotalPrice1").innerHTML = diffPrice;
                document.getElementById("cartTotalPrice2").innerHTML = diffPrice;
                document.getElementById("cartTotalPrice3").innerHTML = diffPrice;
                row.parentNode.removeChild(row);
                allchangedOrders = changedOrders.changedOrders;
                orderIndex = allchangedOrders.findIndex(order => order.orderId == orderId1);
                if (orderIndex == -1) {
                    allchangedOrders.push({"orderId": orderId1, "quantity": 0});
                } else {
                    allchangedOrders[orderIndex].quantity = 0;
                }
                rowsCounter = rowsCounter - 1;
                document.getElementById("noOfOrders").innerHTML = " You currently have " + rowsCounter + " item(s) in your cart ."
            }
            function checkOutCart(btn) {
                var table = document.getElementById("cartItemTable");
                for (var i = 1, row; row = table.rows[i]; i++) {
                    //iterate through rows
                    //rows would be accessed using the "row" variable assigned in the for loop
                    if (row.cells[2]){
                        console.log(row.cells[2]);
                        console.log(row.cells[3]);
                    }
                }

            }


        </script>

        <link rel="shortcut icon" href="res/img/favicon.png">


    </head>
    <body>
        <!-- *** TOPBAR ***
 _________________________________________________________ -->
        <div id="top">
            <div class="container">
                <div class="col-md-6 offer" >
                </div>
                <div class="col-md-6" data-animate="fadeInDown">
                    <ul class="menu">
                        <li><a href="#" data-toggle="modal" data-target="#login-modal">Login</a>
                        </li>
                        <li><a href="register.html">Register</a>
                        </li>
                        <li><a href="contact.html">Contact</a>
                        </li>

                    </ul>
                </div>
            </div>
            <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true">
                <div class="modal-dialog modal-sm">

                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="Login">Customer login</h4>
                        </div>
                        <div class="modal-body">
                            <form action="customer-orders.html" method="post">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="email-modal" placeholder="email">
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" id="password-modal" placeholder="password">
                                </div>

                                <p class="text-center">
                                    <button class="btn btn-primary"><i class="fa fa-sign-in"></i> Log in</button>
                                </p>

                            </form>

                            <p class="text-center text-muted">Not registered yet?</p>
                            <p class="text-center text-muted"><a href="register.html"><strong>Register now</strong></a>! It is easy and done in 1&nbsp;minute and gives you access to special discounts and much more!</p>

                        </div>
                    </div>
                </div>
            </div>

        </div>

        <!-- *** TOP BAR END *** -->

        <!-- *** NAVBAR ***
     _________________________________________________________ -->

        <div class="navbar navbar-default yamm" role="navigation" id="navbar">
            <div class="container">
                <div class="navbar-header">

                    <a class="navbar-brand home" href="index.html" data-animate-hover="bounce">
                        <img src="res/img/logo.png" alt="Obaju logo" class="hidden-xs">
                        <img src="res/img/logo-small.png" alt="Obaju logo" class="visible-xs"><span class="sr-only">Obaju - go to homepage</span>
                    </a>
                    <div class="navbar-buttons">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation">
                            <span class="sr-only">Toggle navigation</span>
                            <i class="fa fa-align-justify"></i>
                        </button>
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#search">
                            <span class="sr-only">Toggle search</span>
                            <i class="fa fa-search"></i>
                        </button>
                        <a class="btn btn-default navbar-toggle" href="basket.html">
                            <i class="fa fa-shopping-cart"></i>  <span class="hidden-xs">3 items in cart</span>
                        </a>
                    </div>
                </div>
                <!--/.navbar-header -->

                <div class="navbar-collapse collapse" id="navigation">

                    <ul class="nav navbar-nav navbar-left">
                        <li class="active"><a href="index.html">Home</a>
                        </li>
                        <li class="dropdown yamm-fw">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="200">Categories <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <div class="yamm-content">
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <h5>Clothing</h5>
                                                <ul>
                                                    <li><a href="category.html">T-shirts</a>
                                                    </li>
                                                    <li><a href="category.html">Shirts</a>
                                                    </li>
                                                    <li><a href="category.html">Pants</a>
                                                    </li>
                                                    <li><a href="category.html">Accessories</a>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="col-sm-3">
                                                <h5>Shoes</h5>
                                                <ul>
                                                    <li><a href="category.html">Trainers</a>
                                                    </li>
                                                    <li><a href="category.html">Sandals</a>
                                                    </li>
                                                    <li><a href="category.html">Hiking shoes</a>
                                                    </li>
                                                    <li><a href="category.html">Casual</a>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="col-sm-3">
                                                <h5>Accessories</h5>
                                                <ul>
                                                    <li><a href="category.html">Trainers</a>
                                                    </li>
                                                    <li><a href="category.html">Sandals</a>
                                                    </li>
                                                    <li><a href="category.html">Hiking shoes</a>
                                                    </li>
                                                    <li><a href="category.html">Casual</a>
                                                    </li>
                                                    <li><a href="category.html">Hiking shoes</a>
                                                    </li>
                                                    <li><a href="category.html">Casual</a>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="col-sm-3">
                                                <h5>Featured</h5>
                                                <ul>
                                                    <li><a href="category.html">Trainers</a>
                                                    </li>
                                                    <li><a href="category.html">Sandals</a>
                                                    </li>
                                                    <li><a href="category.html">Hiking shoes</a>
                                                    </li>
                                                </ul>
                                                <h5>Looks and trends</h5>
                                                <ul>
                                                    <li><a href="category.html">Trainers</a>
                                                    </li>
                                                    <li><a href="category.html">Sandals</a>
                                                    </li>
                                                    <li><a href="category.html">Hiking shoes</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.yamm-content -->
                                </li>
                            </ul>
                        </li>

                        <!--                        <li class="dropdown yamm-fw">
                                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="200">Ladies <b class="caret"></b></a>
                                                    <ul class="dropdown-menu">
                                                        <li>
                                                            <div class="yamm-content">
                                                                <div class="row">
                                                                    <div class="col-sm-3">
                                                                        <h5>Clothing</h5>
                                                                        <ul>
                                                                            <li><a href="category.html">T-shirts</a>
                                                                            </li>
                                                                            <li><a href="category.html">Shirts</a>
                                                                            </li>
                                                                            <li><a href="category.html">Pants</a>
                                                                            </li>
                                                                            <li><a href="category.html">Accessories</a>
                                                                            </li>
                                                                        </ul>
                                                                    </div>
                                                                    <div class="col-sm-3">
                                                                        <h5>Shoes</h5>
                                                                        <ul>
                                                                            <li><a href="category.html">Trainers</a>
                                                                            </li>
                                                                            <li><a href="category.html">Sandals</a>
                                                                            </li>
                                                                            <li><a href="category.html">Hiking shoes</a>
                                                                            </li>
                                                                            <li><a href="category.html">Casual</a>
                                                                            </li>
                                                                        </ul>
                                                                    </div>
                                                                    <div class="col-sm-3">
                                                                        <h5>Accessories</h5>
                                                                        <ul>
                                                                            <li><a href="category.html">Trainers</a>
                                                                            </li>
                                                                            <li><a href="category.html">Sandals</a>
                                                                            </li>
                                                                            <li><a href="category.html">Hiking shoes</a>
                                                                            </li>
                                                                            <li><a href="category.html">Casual</a>
                                                                            </li>
                                                                            <li><a href="category.html">Hiking shoes</a>
                                                                            </li>
                                                                            <li><a href="category.html">Casual</a>
                                                                            </li>
                                                                        </ul>
                                                                        <h5>Looks and trends</h5>
                                                                        <ul>
                                                                            <li><a href="category.html">Trainers</a>
                                                                            </li>
                                                                            <li><a href="category.html">Sandals</a>
                                                                            </li>
                                                                            <li><a href="category.html">Hiking shoes</a>
                                                                            </li>
                                                                        </ul>
                                                                    </div>
                                                                    <div class="col-sm-3">
                                                                        <div class="banner">
                                                                            <a href="#">
                                                                                <img src="res/img/banner.jpg" class="res/img img-responsive" alt="">
                                                                            </a>
                                                                        </div>
                                                                        <div class="banner">
                                                                            <a href="#">
                                                                                <img src="res/img/banner2.jpg" class="res/img img-responsive" alt="">
                                                                            </a>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                             /.yamm-content 
                                                        </li>
                                                    </ul>
                                                </li>-->

                        <li class="dropdown yamm-fw">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="200">Template <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <div class="yamm-content">
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <h5>Shop</h5>
                                                <ul>
                                                    <li><a href="index.html">Homepage</a>
                                                    </li>
                                                    <li><a href="category.html">Category - sidebar left</a>
                                                    </li>
                                                    <li><a href="category-right.html">Category - sidebar right</a>
                                                    </li>
                                                    <li><a href="category-full.html">Category - full width</a>
                                                    </li>
                                                    <li><a href="detail.html">Product detail</a>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="col-sm-3">
                                                <h5>User</h5>
                                                <ul>
                                                    <li><a href="register.html">Register / login</a>
                                                    </li>
                                                    <li><a href="customer-orders.html">Orders history</a>
                                                    </li>
                                                    <li><a href="customer-order.html">Order history detail</a>
                                                    </li>
                                                    <li><a href="customer-wishlist.html">Wishlist</a>
                                                    </li>
                                                    <li><a href="customer-account.html">Customer account / change password</a>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="col-sm-3">
                                                <h5>Order process</h5>
                                                <ul>
                                                    <li><a href="basket.html">Shopping cart</a>
                                                    </li>
                                                    <li><a href="checkout1.html">Checkout - step 1</a>
                                                    </li>
                                                    <li><a href="checkout2.html">Checkout - step 2</a>
                                                    </li>
                                                    <li><a href="checkout3.html">Checkout - step 3</a>
                                                    </li>
                                                    <li><a href="checkout4.html">Checkout - step 4</a>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="col-sm-3">
                                                <h5>Pages and blog</h5>
                                                <ul>
                                                    <li><a href="blog.html">Blog listing</a>
                                                    </li>
                                                    <li><a href="post.html">Blog Post</a>
                                                    </li>
                                                    <li><a href="faq.html">FAQ</a>
                                                    </li>
                                                    <li><a href="text.html">Text page</a>
                                                    </li>
                                                    <li><a href="text-right.html">Text page - right sidebar</a>
                                                    </li>
                                                    <li><a href="404.html">404 page</a>
                                                    </li>
                                                    <li><a href="contact.html">Contact</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.yamm-content -->
                                </li>
                            </ul>
                        </li>
                    </ul>

                </div>
                <!--/.nav-collapse -->

                <div class="navbar-buttons">

                    <div class="navbar-collapse collapse right" id="basket-overview">
                        <a href="basket.html" class="btn btn-primary navbar-btn"><i class="fa fa-shopping-cart"></i><span class="hidden-sm">3 items in cart</span></a>
                    </div>
                    <!--/.nav-collapse -->

                    <div class="navbar-collapse collapse right" id="search-not-mobile">
                        <button type="button" class="btn navbar-btn btn-primary" data-toggle="collapse" data-target="#search">
                            <span class="sr-only">Toggle search</span>
                            <i class="fa fa-search"></i>
                        </button>
                    </div>

                </div>

                <div class="collapse clearfix" id="search">

                    <form class="navbar-form" role="search">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Search">
                            <span class="input-group-btn">

                                <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i></button>

                            </span>
                        </div>
                    </form>

                </div>
                <!--/.nav-collapse -->

            </div>
            <!-- /.container -->
        </div>
        <!-- /#navbar -->

        <!-- *** NAVBAR END *** -->

        <div id="all">

            <div id="content">
                <div class="container">

                    <div class="col-md-12">
                        <ul class="breadcrumb">
                            <li><a href="index.html">Home</a>
                            </li>
                            <li>Shopping cart</li>
                        </ul>
                    </div>

                    <div class="col-md-9" id="basket">

                        <div class="box">

                            <!--<form method="post" action="">-->

                            <c:set var = "totalprice" scope = "session" value = "${0}"/>
                            <c:set var = "varNoOfOrders" scope = "session" value ="${Items.size()}"/>
                            <h1>Shopping cart</h1>
                            <p id="noOfOrders" class="text-muted">You currently have ${Items.size()} item(s) in your cart .</p>
                            <div class="table-responsive">
                                <table  id="cartItemTable" class="table">
                                    <thead>
                                        <tr>
                                            <th colspan="2">Product</th>
                                            <th>Available Quantity</th>
                                            <th>Quantity</th>
                                            <th>Unit price</th>
                                            <th>Discount</th>
                                            <th colspan="2">Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${sessionScope.Items}" var="order">
                                            <!--<tr class="{(${order.orderProduct.availableQuantity}  ${order.quantity}) ? danger  :active}">-->
                                            <c:if test="${order.orderProduct.availableQuantity lt order.quantity}">
                                                <tr class="danger">
                                                </c:if>
                                                <c:if test="${order.orderProduct.availableQuantity gt order.quantity}">

                                                <tr>
                                                </c:if>

                                                <td>
                                                    <a href="#">
                                                        <img src="res/img/basketsquare.jpg" alt="Black Blouse Armani">
                                                    </a>
                                                </td>
                                                <td><a href="#">${order.orderProduct.productName}</a>
                                                </td>
                                                <td>
                                                    ${order.orderProduct.availableQuantity}
                                                </td>
                                                <td>
                                                    <input type="number" id="${order.orderId}" onchange="updateQuantity(this,${order.orderProduct.productPrice},${order.orderProduct.availableQuantity})" value=${order.quantity} min="1" max=${order.orderProduct.availableQuantity} class="form-control mynumber">
                                                           <!--<input type="number"  value="5" min="1" max="10" class="form-control mynumber">-->
                                                </td>
                                                <td>${order.orderProduct.productPrice}</td>
                                                <td>$0.00</td>
                                                <td id="orderPrice${order.orderId}">${order.orderProduct.productPrice*order.quantity}</td>
                                                <c:set var="totalprice" value="${totalprice+(order.orderProduct.productPrice*order.quantity)}"/>
                                                <td><i class="fa fa-trash-o myItem" onclick="deleteRow(this,${order.orderId})"></i>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th colspan="5">Total</th>
                                            <th id="cartTotalPrice1" colspan="2"><c:out value = "${totalprice}"/></th>
                                        </tr>
                                    </tfoot>
                                </table>

                            </div>
                            <!-- /.table-responsive -->

                            <div class="box-footer row">
                                <div class="pull-left">
                                    <a href="index.html" class="btn btn-default"><i class="fa fa-chevron-left"></i> Continue shopping</a>
                                </div>
                                <div class="pull-right">

                                    <div   class="col-md-5">
                                        <button id="updateCartBtn" class="btn btn-default" onclick="updateCart(this)" disabled="true"><i class="fa fa-refresh" ></i> Update basket</button>
                                    </div>
                                    <div class="col-md-5">
                                        <button id="checkoutBtn" onclick="checkOutCart(this)" class="btn btn-primary">Proceed to checkout <i class="fa fa-chevron-right"></i>
                                        </button>
                                    </div>     
                                </div>
                            </div>


                        </div>
                        <!-- /.box -->



                    </div>  
                    <!-- /.col-md-9 -->

                    <div class="col-md-3">
                        <div class="box" id="order-summary">
                            <div class="box-header">
                                <h3>Order summary</h3>
                            </div>
                            <p class="text-muted">Shipping and additional costs are calculated based on the values you have entered.</p>

                            <div class="table-responsive">
                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <td>Order subtotal</td>
                                            <th id="cartTotalPrice2"><c:out value = "${totalprice}"/></th>
                                        </tr>
                                        <tr>
                                            <td>Shipping and handling</td>
                                            <th>$0.00</th>
                                        </tr>
                                        <tr>
                                            <td>Tax</td>
                                            <th>$0.00</th>
                                        </tr>
                                        <tr class="total">
                                            <td>Total</td>
                                            <th id="cartTotalPrice3"><c:out value = "${totalprice}"/></th>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                        </div>


                    </div>
                    <!-- /.col-md-3 -->

                </div>
                <!-- /.container -->
            </div>
            <!-- /#content -->

            <!-- *** FOOTER ***
     _________________________________________________________ -->
            <div id="footer" data-animate="fadeInUp">
                <div class="container">
                    <div class="row">
                        <div class="col-md-3 col-sm-6">
                            <h4>Pages</h4>

                            <ul>
                                <li><a href="text.html">About us</a>
                                </li>
                                <li><a href="text.html">Terms and conditions</a>
                                </li>
                                <li><a href="faq.html">FAQ</a>
                                </li>
                                <li><a href="contact.html">Contact us</a>
                                </li>
                            </ul>

                            <hr>

                            <h4>User section</h4>

                            <ul>
                                <li><a href="#" data-toggle="modal" data-target="#login-modal">Login</a>
                                </li>
                                <li><a href="register.html">Regiter</a>
                                </li>
                            </ul>

                            <hr class="hidden-md hidden-lg hidden-sm">

                        </div>
                        <!-- /.col-md-3 -->

                        <div class="col-md-3 col-sm-6">

                            <h4>Top categories</h4>

                            <h5>Men</h5>

                            <ul>
                                <li><a href="category.html">T-shirts</a>
                                </li>
                                <li><a href="category.html">Shirts</a>
                                </li>
                                <li><a href="category.html">Accessories</a>
                                </li>
                            </ul>

                            <h5>Ladies</h5>
                            <ul>
                                <li><a href="category.html">T-shirts</a>
                                </li>
                                <li><a href="category.html">Skirts</a>
                                </li>
                                <li><a href="category.html">Pants</a>
                                </li>
                                <li><a href="category.html">Accessories</a>
                                </li>
                            </ul>

                            <hr class="hidden-md hidden-lg">

                        </div>
                        <!-- /.col-md-3 -->

                        <div class="col-md-3 col-sm-6">

                            <h4>Where to find us</h4>

                            <p><strong>Obaju Ltd.</strong>
                                <br>13/25 New Avenue
                                <br>New Heaven
                                <br>45Y 73J
                                <br>England
                                <br>
                                <strong>Great Britain</strong>
                            </p>

                            <a href="contact.html">Go to contact page</a>

                            <hr class="hidden-md hidden-lg">

                        </div>
                        <!-- /.col-md-3 -->



                        <div class="col-md-3 col-sm-6">

                            <h4>Get the news</h4>

                            <p class="text-muted">Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.</p>

                            <form>
                                <div class="input-group">

                                    <input type="text" class="form-control">

                                    <span class="input-group-btn">

                                        <button class="btn btn-default" type="button">Subscribe!</button>

                                    </span>

                                </div>
                                <!-- /input-group -->
                            </form>

                            <hr>

                            <h4>Stay in touch</h4>

                            <p class="social">
                                <a href="#" class="facebook external" data-animate-hover="shake"><i class="fa fa-facebook"></i></a>
                                <a href="#" class="twitter external" data-animate-hover="shake"><i class="fa fa-twitter"></i></a>
                                <a href="#" class="instagram external" data-animate-hover="shake"><i class="fa fa-instagram"></i></a>
                                <a href="#" class="gplus external" data-animate-hover="shake"><i class="fa fa-google-plus"></i></a>
                                <a href="#" class="email external" data-animate-hover="shake"><i class="fa fa-envelope"></i></a>
                            </p>


                        </div>
                        <!-- /.col-md-3 -->

                    </div>
                    <!-- /.row -->

                </div>
                <!-- /.container -->
            </div>
            <!-- /#footer -->

            <!-- *** FOOTER END *** -->




            <!-- *** COPYRIGHT ***
     _________________________________________________________ -->
            <div id="copyright">
                <div class="container">
                    <div class="col-md-6">
                        <p class="pull-left">© 2015 Your name goes here.</p>

                    </div>
                    <div class="col-md-6">
                        <p class="pull-right">Template by <a href="https://bootstrapious.com/e-commerce-templates">Bootstrapious</a> & <a href="https://fity.cz">Fity</a>
                            <!-- Not removing these links is part of the license conditions of the template. Thanks for understanding :) If you want to use the template without the attribution links, you can do so after supporting further themes development at https://bootstrapious.com/donate  -->
                        </p>
                    </div>
                </div>
            </div>
            <!-- *** COPYRIGHT END *** -->



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
