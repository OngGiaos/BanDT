<%-- 
    Document   : ListUser
    Created on : Jul 17, 2022, 2:17:32 PM
    Author     : TGDD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List User</title>
    </head>
    <body>
        <table border="1" style="text-align: center">
            <tr>
                <td>Account</td>
                <td>Password</td><!-- comment -->
                <td>User Name</td>
                <td>Address</td><!-- comment -->
                <td>City</td><!-- comment -->
                <td>Country</td>
                <td>Zip code</td><!-- comment -->
                <td>Phone Number</td><!-- comment -->
                <td>Number Items in Wishlist</td>
                <td>Wishlist</td>
            </tr>
            <c:forEach items="${listU}" var="u">
                <tr>
                    <td>${u.email}</td>
                    <td>${u.pass}</td><!-- comment -->
                    <td>${u.name}</td>
                    <td>${u.address}</td><!-- comment -->
                    <td>${u.city}</td><!-- comment -->
                    <td>${u.country}</td>
                    <td>${u.zip}</td><!-- comment -->
                    <td>${u.phone}</td><!-- comment -->
                    <td>
                        <c:forEach items="${listNumberItem}" var="N">
                            <c:if test="${u.email.equals(N.email)}">
                                ${N.number}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td><a href="ViewWishlist?email=${u.email}">View</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
