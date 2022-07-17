<%-- 
    Document   : Admin
    Created on : Jul 17, 2022, 1:56:07 PM
    Author     : TGDD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <!<!-- http://localhost:9999/AssignmentPRJ/Admin -->
        <form action="Admin" method="POST">
            <table>
                <tr>
                    <td>List User: </td>
                    <td><input type="submit" name="listUser" value="List User"></td>
                </tr>
                <tr>
                    <td>List Product: </td>
                    <td><input type="submit" name="listProduct" value="List Product"></td>
                </tr><!-- comment -->

                <tr>
                    <td>Sold List: </td>
                    <td><input type="submit" name="sold" value="Sold List"></td>
                </tr>
                <tr>
                    <td><a href="Home">Go to Home page</a></td>
                </tr>
            </table>
        </form>
    </body>
</html>
