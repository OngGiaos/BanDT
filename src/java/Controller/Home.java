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
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author TGDD
 */
public class Home extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO pd = new ProductDAO();
        OrderDAO o = new OrderDAO();
        ArrayList<Product> productList = new ArrayList<Product>();
        productList = pd.downloadData();
        ArrayList<Order> order = new ArrayList<Order>();
        ArrayList<Order> wish = new ArrayList<Order>();
        HttpSession session = request.getSession();
        String searchIn = request.getParameter("searchIn");
        String notice = "";
        if (request.getParameter("search") != null) {
            productList = pd.search(searchIn);
            if (productList.size() > 1) {
                notice = "There are have " + productList.size() + " product(s) found!";
            } else {
                notice = "There is have " + productList.size() + " product(s) found!";
            }
        }
        int wishSize = 0;
        if (session.getAttribute("user") != null) {
            User u = (User) session.getAttribute("user");
            order = o.orderData(u);
            wish = o.wishlist(u);
            for (Order w : wish) {
                if (w.getPhone().isShow()) {
                    wishSize++;
                }
            }
            long t = o.getTotal(order);
            String total = o.convertPrice(t);
            session.setAttribute("total", total);
        }
        
        request.setAttribute("notice", notice);
        session.setAttribute("phone_see", productList);
        session.setAttribute("order", order);
        session.setAttribute("wish", wish);
        session.setAttribute("wishSize", wishSize);
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
