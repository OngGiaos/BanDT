<%-- 
    Document   : ListProduct
    Created on : Jul 17, 2022, 3:57:17 PM
    Author     : TGDD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Product</title>
    </head>
    <body>
        <h3><a href="Admin">Go to admin page</a></h3>
        <table border="1" style="text-align: center">
            <tr>
                <td>Phone ID</td>
                <td>Creator</td><!-- comment -->
                <td>Phone Name</td>
                <td>Unit Price</td><!-- comment -->
                <td>Image</td><!-- comment -->
                <td>Sold</td>
                <td>Quantity</td><!-- comment -->
                <td>isShow</td><!-- comment -->
                <td></td>
                <td></td>
            </tr>
            <c:forEach items="${listP}" var="P">
                <tr>
                    <td>${P.id}</td>
                    <td>${P.creator}</td><!-- comment -->
                    <td>${P.name}</td>
                    <td>${P.price}</td><!-- comment -->
                    <td><img style="height: 90px; width: 60px" src="${P.img}" alt="${P.img}"/></td><!-- comment -->
                    <td>${P.sold}</td>
                    <td>${P.quantity}</td><!-- comment -->
                    <td>${P.show}</td><!-- comment -->
                    <td><a href="UpdateProduct?id=${P.id}">Update</a></td>
                    <c:choose>
                        <c:when test="${P.show==false}">
                            <td><a href="ListProduct?id=${P.id}&isShow=1">Show</a></td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="ListProduct?id=${P.id}&isShow=0">Hide</a></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
