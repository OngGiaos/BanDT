<%-- 
    Document   : ViewWishlist
    Created on : Jul 17, 2022, 3:39:37 PM
    Author     : TGDD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${wishlist.size()>0}">
            <table border="1" style="text-align: center">
                <tr>
                    <td>Phone ID</td>
                    <td>Phone Name</td>
                </tr>
                <c:forEach items="${wishlist}" var="w">
                    <tr>
                        <td>${w.phone.id}</td>
                        <td>${w.phone.name}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <h3><a href="Admin">Go to admin page</a></h3>
    </body>
</html>
