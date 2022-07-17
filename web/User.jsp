<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Electro - HTML Ecommerce Template</title>

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>

        <!-- Slick -->
        <link type="text/css" rel="stylesheet" href="css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>

        <!-- nouislider -->
        <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>

        <!-- Font Awesome Icon -->
        <link rel="stylesheet" href="css/font-awesome.min.css">

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="css/style.css"/>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>
    <body>
        <!-- HEADER -->
        <form id="updateform" action="updateProfile"></form>
        <form id="change" action="update"></form>
        <header>
            <!-- TOP HEADER -->
            <div id="top-header">
                <div class="container">
                    <ul class="header-links pull-left">
                        <li><a href="#"><i class="fa fa-phone"></i> +0988668899</a></li>
                        <li><a href="#"><i class="fa fa-envelope-o"></i> smartphoneeniver@email.com</a></li>
                        <li><a href="#"><i class="fa fa-map-marker"></i> FPT Univer</a></li>
                    </ul>
                    <c:choose>
                        <c:when test="${user!=null}">
                            <ul class="header-links pull-right">
                                <li><a href="UserInformation"><i class="fa fa-user-o"></i> ${user.name} </a></li>
                                <li><a href="LogoutController"><i class="fa fa-sign-out"></i> Logout </a></li>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <ul class="header-links pull-right">
                                <li><a href="Login.jsp"><i class="fa fa-user-o"></i> Login </a></li> 	
                                <li><a href="Register.jsp"><i class="fa fa-user-o"></i> Register </a></li> 	
                            </ul>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <!-- /TOP HEADER -->

            <!-- MAIN HEADER -->
            <div id="header">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <!-- LOGO -->
                        <div class="col-md-3">
                            <div class="header-logo">
                                <a href="Home" class="logo">
                                    <img src="./img/logo.png" alt="">
                                </a>
                            </div>
                        </div>
                        <!-- /LOGO -->

                        <!-- SEARCH BAR -->
                        <div class="col-md-6">
                            <div class="header-search">
                                <form action="Home">
                                    <!-- <select class="input-select">
                                            <option value="0">All Categories</option>
                                            <option value="1">Category 01</option>
                                            <option value="1">Category 02</option>
                                    </select> -->
                                    <input class="input"  type="text" name="searchIn" value="${searchIn}" placeholder="Search">
                                    <button class="search-btn" type="submit" name="search" value="ok">Search</button>
                            </div>
                        </div>
                        <!-- /SEARCH BAR -->

                        <!-- ACCOUNT -->
                        <div class="col-md-3 clearfix">
                            <div class="header-ctn">
                                <c:if test="${user!=null}">
                                    <!-- Wishlist -->
                                    <div class="dropdown">
                                        <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                            <i class="fa fa-heart"></i>
                                            <span>Wishlist</span>

                                            <div class="qty">${wishSize}</div>
                                        </a>
                                        <div class="cart-dropdown">
                                            <div class="cart-list">
                                                <c:forEach items="${wish}" var="w">
                                                    <c:if test="${w.phone.show==true}">
                                                        <div class="product-widget">
                                                            <div class="product-img">
                                                                <img src="${w.phone.img}" alt="">
                                                            </div>
                                                            <div class="product-body">
                                                                <h3 class="product-name"><a href="ViewDetail?id=${w.phone.id}">${w.phone.name}</a></h3>
                                                                <h4 class="product-price"><span class="qty">${w.quantity}x</span>${w.phone.price}VND</h4>
                                                            </div>
                                                            <button class="delete"><a href="deleteInWishlist?id=${w.phone.id}&page=home" class="fa fa-close"></a></button>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                            <div class="cart-summary">
                                                <small>${wishSize} Item(s) selected</small>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /Wishlist -->

                                    <!-- Cart -->

                                    <div class="dropdown">
                                        <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                            <i class="fa fa-shopping-cart"></i>
                                            <span>Your Cart</span>

                                            <div class="qty">${order.size()}</div>
                                        </a>
                                        <div class="cart-dropdown">
                                            <div class="cart-list">
                                                <c:forEach items="${order}" var="o">
                                                    <div class="product-widget">
                                                        <div class="product-img">
                                                            <img src="${o.phone.img}" alt="">
                                                        </div>
                                                        <div class="product-body">
                                                            <h3 class="product-name"><a href="ViewDetail?id=${o.phone.id}">${o.phone.name}</a></h3>
                                                            <h4 class="product-price"><span class="qty">${o.quantity}x</span>${o.phone.price}VND</h4>
                                                        </div>
                                                        <button class="delete"><a href="deleteInCart?id=${o.phone.id}&page=profile" class="fa fa-close"></a></button>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <div class="cart-summary">
                                                <small>${order.size()} Item(s) selected</small>
                                                <h5>SUBTOTAL: ${total}VND</h5>
                                            </div>
                                            <c:if test="${order.size()!=0}">
                                                <div class="cart-btns">
                                                    <div></div>
                                                    <a href="checkout.jsp">Checkout  <i class="fa fa-arrow-circle-right"></i></a>
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                    <!-- /Cart -->
                                </c:if>
                                <!-- Menu Toogle -->
                                <div class="menu-toggle">
                                    <a href="#">
                                        <i class="fa fa-bars"></i>
                                        <span>Menu</span>
                                    </a>
                                </div>
                                <!-- /Menu Toogle -->
                            </div>
                        </div>

                        <!-- /ACCOUNT -->
                    </div>
                    <!-- row -->
                </div>
                <!-- container -->
            </div>
            <!-- /MAIN HEADER -->
        </header>
        <!-- /HEADER -->

        <!-- NAVIGATION -->
        <nav id="navigation">

            <div class="container">

                <div id="responsive-nav">

                    <ul class="main-nav nav navbar-nav">
                        <li class="active"><a href="Home">Home</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <!--User Here -->
        <div class="container" style="min-height: 400px">
            <div style="margin-top: 100px" class="main-body">
                <div class="row">
                    <div class="col-lg-4">
                        <div class="card">
                            <div class="card-body">
                                <div class="d-flex flex-column align-items-center text-center">
                                    <img src="https://bootdey.com/img/Content/avatar/avatar6.png" alt="Admin" class="rounded-circle p-1 bg-primary" width="110">
                                    <div class="mt-3">
                                        <h4>${user.name}</h4>
                                        <a href="HistoryOrdersController" class="btn btn-primary">History</a>
                                        <a href="updateProfile" class="btn btn-primary">Setting</a>
                                        <a href="changePassword" class="btn btn-primary">Change Password</a>
                                    </div>
                                </div>
                                <hr class="my-4">

                            </div>
                        </div>
                    </div>

                    <c:choose>
                        <c:when test="${update==true}">
                            <h4>Update Information</h4>
                            <div class="col-lg-8">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Full Name</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                <input form="updateform" required type="text" name="newName" value="${user.name}" style="width: 300px">
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Email</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                <input readonly type="email" name="email" value="${user.email}" style="width: 300px">
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Phone</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                <input form="updateform" required type="text" name="newPhone" value="${user.phone}" style="width: 300px">
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Address</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                <input form="updateform" required type="text" name="newAddress" value="${user.address}" style="width: 300px">
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">City</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                <input form="updateform" required type="text" name="newCity" value="${user.city}" style="width: 300px">
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Country</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                <input form="updateform" required type="text" name="newCountry" value="${user.country}" style="width: 300px">
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Zip code</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                <input form="updateform" required type="text" name="newZip" value="${user.zip}" style="width: 300px">
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Role</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                User
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0"></h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                <input form="updateform" type="submit" name="save" value="Save">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${change==true}">
                            <h3>Change Password</h3>
                            <table>
                                <tr>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td style="color: red">${note}</td>
                                </tr>
                                <tr>
                                    <td>Old Password </td>
                                    <td><input form="change" type="password" required name="oldPassword"></td>
                                </tr>
                                <tr>
                                    <td>New Password </td>
                                    <td><input form="change" type="password" required name="newPassword"></td>
                                </tr>
                                <tr>
                                    <td>Confirm New Password </td>
                                    <td><input form="change" type="password" required name="confirm"></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><input form="change" type="submit" name="changePass" value="Change"></td>
                                </tr>
                            </table>

                        </c:when>
                        <c:otherwise>
                            <div class="col-lg-8">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="row mb-3">
                                            <h5 style="color: green">${note}</h5>
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Full Name</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                ${user.name}
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Email</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                ${user.email}
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Phone</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                ${user.phone}
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Address</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                ${user.address}
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">City</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                ${user.city}
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Country</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                ${user.country}
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Zip code</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                ${user.zip}
                                            </div>
                                        </div>

                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Role</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                User
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <!--User Here -->

        <!-- FOOTER -->
        <footer id="footer">
            <!-- top footer -->
            <div class="section">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <div class="col-md-3 col-xs-6">
                            <div class="footer">
                                <h3 class="footer-title">About Us</h3>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut.</p>
                                <ul class="footer-links">
                                    <li><a href="#"><i class="fa fa-map-marker"></i>1734 Stonecoal Road</a></li>
                                    <li><a href="#"><i class="fa fa-phone"></i>+021-95-51-84</a></li>
                                    <li><a href="#"><i class="fa fa-envelope-o"></i>email@email.com</a></li>
                                </ul>
                            </div>
                        </div>

                        <div class="col-md-3 col-xs-6">
                            <div class="footer">
                                <h3 class="footer-title">Categories</h3>
                                <ul class="footer-links">
                                    <li><a href="#">Hot deals</a></li>
                                    <li><a href="#">Laptops</a></li>
                                    <li><a href="#">Smartphones</a></li>
                                    <li><a href="#">Cameras</a></li>
                                    <li><a href="#">Accessories</a></li>
                                </ul>
                            </div>
                        </div>

                        <div class="clearfix visible-xs"></div>

                        <div class="col-md-3 col-xs-6">
                            <div class="footer">
                                <h3 class="footer-title">Information</h3>
                                <ul class="footer-links">
                                    <li><a href="#">About Us</a></li>
                                    <li><a href="#">Contact Us</a></li>
                                    <li><a href="#">Privacy Policy</a></li>
                                    <li><a href="#">Orders and Returns</a></li>
                                    <li><a href="#">Terms & Conditions</a></li>
                                </ul>
                            </div>
                        </div>

                        <div class="col-md-3 col-xs-6">
                            <div class="footer">
                                <h3 class="footer-title">Service</h3>
                                <ul class="footer-links">
                                    <li><a href="#">My Account</a></li>
                                    <li><a href="#">View Cart</a></li>
                                    <li><a href="#">Wishlist</a></li>
                                    <li><a href="#">Track My Order</a></li>
                                    <li><a href="#">Help</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- /row -->
                </div>
                <!-- /container -->
            </div>
            <!-- /top footer -->

            <!-- bottom footer -->
            <div id="bottom-footer" class="section">
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <div class="col-md-12 text-center">
                            <ul class="footer-payments">
                                <li><a href="#"><i class="fa fa-cc-visa"></i></a></li>
                                <li><a href="#"><i class="fa fa-credit-card"></i></a></li>
                                <li><a href="#"><i class="fa fa-cc-paypal"></i></a></li>
                                <li><a href="#"><i class="fa fa-cc-mastercard"></i></a></li>
                                <li><a href="#"><i class="fa fa-cc-discover"></i></a></li>
                                <li><a href="#"><i class="fa fa-cc-amex"></i></a></li>
                            </ul>
                            <span class="copyright">
                                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                                Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                            </span>
                        </div>
                    </div>
                    <!-- /row -->
                </div>
                <!-- /container -->
            </div>
            <!-- /bottom footer -->
        </footer>
        <!-- /FOOTER -->

        <!-- jQuery Plugins -->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/slick.min.js"></script>
        <script src="js/nouislider.min.js"></script>
        <script src="js/jquery.zoom.min.js"></script>
        <script src="js/main.js"></script>

    </body>
</html>
