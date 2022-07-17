/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Order;
import Model.OrderDAO;
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
public class UserInformation extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().removeAttribute("update");
//        request.getSession().removeAttribute("change");
        OrderDAO od = new OrderDAO();
        User user = (User) request.getSession().getAttribute("user");
        ArrayList<Order> order = od.orderData(user);
        ArrayList<Order> wish = od.wishlist(user);
        long t = od.getTotal(order);
        String total = od.convertPrice(t);
        request.getSession().setAttribute("total", total);
        request.getSession().setAttribute("wish", wish);
        request.getSession().setAttribute("order", order);
        request.getRequestDispatcher("User.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
