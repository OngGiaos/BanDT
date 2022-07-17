<%-- 
    Document   : UpdateProduct
    Created on : Jul 17, 2022, 4:26:11 PM
    Author     : TGDD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product Page</title>
    </head>
    <body>
        <form action="UpdateProduct" method="POST">
            <h3><a href="Admin">Go to admin page</a></h3>
            <input hidden type="text" name="id" value="${phoneUpdate.id}">
            <input hidden type="text" name="isShow" value="${phoneUpdate.show}">
            <table style="text-align: center">
                <tr>
                    <td>Phone ID</td>
                    <td>Creator</td><!-- comment -->
                    <td>Phone Name</td>
                    <td>Unit Price</td><!-- comment -->
                    <td>Image</td><!-- comment -->
                    <td>Sold</td>
                    <td>Quantity</td><!-- comment -->
                    
                    <td>Phone Information</td>
                    <td></td>
                </tr>
                <tr>
                    <td>${phoneUpdate.id}</td>
                    <td><input required type="text" name="creator" value="${phoneUpdate.creator}"></td><!-- comment -->
                    <td><input required type="text" name="name" value="${phoneUpdate.name}"></td>
                    <td><input required type="number" min="0" name="price" value="${priceConverted}"></td><!-- comment -->
                    <td><input required type="text" name="img" value="${phoneUpdate.img}"></td><!-- comment -->
                    <td><input required type="number" min="0" name="sold" value="${phoneUpdate.sold}"></td>
                    <td><input required type="number" min="0" name="quantity" value="${phoneUpdate.quantity}"></td><!-- comment -->
                    <td><textarea cols="20" rows="5" name="info">${phoneUpdate.information}</textarea></td>
                    <td><input type="submit" name="save" value="Save"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
