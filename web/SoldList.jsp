<%-- 
    Document   : SoldList
    Created on : Jul 17, 2022, 5:43:14 PM
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
        <h3><a href="Admin">Go to admin page</a></h3>
        <table border="1" style="text-align: center">
            <tr>
                <td>Order Date</td>
                <td>Account</td><!-- comment -->
                <td>Phone ID</td>
                <td>Phone Name</td><!-- comment -->
                <td>Unit Price</td><!-- comment -->
                <td>Quantity</td>  
                
            </tr>
            <c:forEach items="${listS}" var="s">
                <tr>
                    <td>${s.date}</td>
                    <td>${s.account}</td><!-- comment -->
                    <td>${s.pid}</td>
                    <td>${s.pname}</td><!-- comment -->
                    <td>${s.pprice}</td>
                    <td>${s.quantity}</td><!-- comment -->
                    
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
