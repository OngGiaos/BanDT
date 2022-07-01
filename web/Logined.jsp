<%-- 
    Document   : Logined
    Created on : Jul 1, 2022, 1:43:17 PM
    Author     : TGDD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%                                
          
                if(session.getAttribute("session") != null){
                    String name = request.getAttribute("name");
                    out.print("<li><a href=\"#\"><i class=\"fa fa-user-o\"></i> " + name + "</a></li>");
                } else {
                    out.print("<li><a href=\"http://localhost:9999/AssignmentPRJ/Login.jsp \"><i class=\"fa fa-user-o\"></i>Login</a></li>");
                }
            
        %>
    </body>
</html>
