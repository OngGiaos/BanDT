/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Order;
import Model.OrderDAO;
import Model.Product;
import Model.ProductDAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author TGDD
 */
public class ViewDetail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get phone to display
        String id = request.getParameter("id");
        request.getSession().setAttribute("viewid", id);
        ProductDAO pd = new ProductDAO();

        ArrayList<Product> productList = new ArrayList<Product>();
        productList = pd.downloadData();
        request.getSession().setAttribute("phone_see", productList);

        Product thisphone = pd.getPhone(id);
        request.setAttribute("thisphone", thisphone);

        //wish list and cart
        OrderDAO od = new OrderDAO();
        User user = (User) request.getSession().getAttribute("user");
        ArrayList<Order> order = od.orderData(user);
        ArrayList<Order> wish = od.wishlist(user);
        long t = od.getTotal(order);
        String total = od.convertPrice(t);
        request.getSession().setAttribute("total", total);

        request.getSession().setAttribute("wish", wish);
        request.getSession().setAttribute("order", order);

        request.getRequestDispatcher("PhoneDetail.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
