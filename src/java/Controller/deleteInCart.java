/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.OrderDAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author TGDD
 */
public class deleteInCart extends HttpServlet {

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
        OrderDAO o = new OrderDAO();
        String id = request.getParameter("id");
        String page = request.getParameter("page");
        User user = (User) request.getSession().getAttribute("user");
        o.deleteProductFromCart(user, id);
        if (page.equals("home")) {
            response.sendRedirect("Home");
        } else if (page.equals("detail")) {
            response.sendRedirect("ViewDetail?id=" + request.getSession().getAttribute("viewid"));
        } else if (page.equals("checkout")) {
            response.sendRedirect("CheckOutController");
        } else if (page.equals("profile")) {
            response.sendRedirect("UserInformation");
        }

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
