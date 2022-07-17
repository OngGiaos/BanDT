/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminPacket;

import Model.OrderDAO;
import Model.Product;
import Model.ProductDAO;
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
public class UpdateProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        ProductDAO pd = new ProductDAO();
        OrderDAO od = new OrderDAO();
        Product phoneUpdate = pd.getPhone(id);
        String priceConverted = od.convertPrice(phoneUpdate.getPrice());
        
        request.getSession().setAttribute("priceConverted", priceConverted);
        request.getSession().setAttribute("phoneUpdate", phoneUpdate);
        request.getRequestDispatcher("UpdateProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String creator = request.getParameter("creator");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String info = request.getParameter("info");
        String img = request.getParameter("img");
        String sold = request.getParameter("sold");
        String quantity = request.getParameter("quantity");
        String isShow = request.getParameter("isShow");
        ProductDAO pd = new ProductDAO();
        OrderDAO od = new OrderDAO();
        long t = Long.parseLong(price);
        price = od.convertPrice(t);
        Product newP = new Product(Integer.parseInt(id), creator, name, price, info, img, Integer.parseInt(sold), Integer.parseInt(quantity), Boolean.parseBoolean(isShow));
        pd.updateProduct(newP);
        ArrayList<Product> listP = pd.downloadData();

        request.getSession().setAttribute("listP", listP);
        response.sendRedirect("ListProduct.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
