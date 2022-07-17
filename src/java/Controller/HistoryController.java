/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.History;
import Model.HistoryDAO;
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
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author TGDD
 */
public class HistoryController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String amount = request.getParameter("amount");
        if (!amount.equals("0")) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String country = request.getParameter("country");
            String zip = request.getParameter("zip");
            String tel = request.getParameter("tel");
            String note = request.getParameter("note");
            LocalDate now = LocalDate.now();
            User user = (User) request.getSession().getAttribute("user");
            OrderDAO od = new OrderDAO();
            ProductDAO pd = new ProductDAO();
            ArrayList<Order> order = od.orderData(user);
            HistoryDAO hd = new HistoryDAO();
            History his = new History(name, email, address, city, country, zip, tel, note, now, order);
            hd.toHistory(user, his);
            //giam so luong hang
            for (Order order1 : order) {
                pd.updateQuantityAndSold(order1.getPhone().getId(), order1.getQuantity());
            }
            //xoa bo order list
            od.deleteAllOrder(user);
        }
        response.sendRedirect("Home");

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
