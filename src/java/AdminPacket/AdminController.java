/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminPacket;

import Model.HistoryDAO;
import Model.NumberOfItem;
import Model.Product;
import Model.ProductDAO;
import Model.Sold;
import Model.User;
import Model.UserDAO;
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
public class AdminController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("listUser") != null) {
            UserDAO ud = new UserDAO();
            ArrayList<User> listU = ud.getAllUser();
            ArrayList<NumberOfItem> num = ud.getNumberOfItem();
            
            request.getSession().setAttribute("listNumberItem", num);
            request.getSession().setAttribute("listU", listU);
            response.sendRedirect("ListUser.jsp");
        }
        if (request.getParameter("listProduct") != null) {
            ProductDAO pd = new ProductDAO();
            ArrayList<Product> listP = pd.downloadData();
            
            request.getSession().setAttribute("listP", listP);
            response.sendRedirect("ListProduct.jsp");
        }
        if (request.getParameter("sold") != null) {
            HistoryDAO hd = new HistoryDAO();
            ArrayList<Sold> listS = hd.getAllSold();
            
            request.getSession().setAttribute("listS", listS);
            response.sendRedirect("SoldList.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
